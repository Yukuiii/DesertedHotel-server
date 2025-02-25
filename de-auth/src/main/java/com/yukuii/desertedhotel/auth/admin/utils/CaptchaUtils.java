package com.yukuii.desertedhotel.auth.admin.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CaptchaUtils {

    private final StringRedisTemplate redisTemplate;
    
    @Value("90")
    private Long captchaExpireSeconds;
    
    // 验证码图片宽度
    private static final int WIDTH = 120;
    // 验证码图片高度
    private static final int HEIGHT = 40;
    // 验证码长度
    private static final int CODE_LENGTH = 4;
    // 验证码字符集
    private static final char[] CODE_SEQUENCE = { 
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
            '2', '3', '4', '5', '6', '7', '8', '9' };
    
    /**
     * 生成验证码并存储到Redis
     * @return 包含验证码图片Base64和验证码ID的对象
     */
    public CaptchaVO generateCaptcha() {
        // 创建图像缓冲区
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // 设置边框
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        
        // 添加干扰线
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.setColor(getRandColor(160, 200));
            g.drawLine(x, y, x + xl, y + yl);
        }
        
        // 添加噪点
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            g.setColor(getRandColor(100, 150));
            g.drawOval(x, y, 2, 2);
        }
        
        // 生成随机验证码
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            String strRand = String.valueOf(CODE_SEQUENCE[random.nextInt(CODE_SEQUENCE.length)]);
            code.append(strRand);
            
            // 将验证码显示到图像中
            g.setColor(getRandColor(20, 130));
            g.setFont(new Font("Arial", Font.BOLD, 30));
            
            // 随机旋转-30到30度
            int degree = random.nextInt(60) - 30;
            g.translate(32 * i + 8, 28);
            g.rotate(degree * Math.PI / 180);
            g.drawString(strRand, 0, 0);
            g.rotate(-degree * Math.PI / 180);
            g.translate(-(32 * i + 8), -28);
        }
        
        g.dispose();
        
        // 将图像转换为Base64字符串
        String base64Image = imageToBase64(image);
        
        // 生成唯一ID
        String captchaId = generateCaptchaId();
        
        // 存储验证码到Redis
        redisTemplate.opsForValue().set("captcha:" + captchaId, code.toString(), captchaExpireSeconds, TimeUnit.SECONDS);
        
        return new CaptchaVO(captchaId, base64Image);
    }
    
    /**
     * 验证验证码
     * @param captchaId 验证码ID
     * @param captchaCode 用户输入的验证码
     * @return 是否验证成功
     */
    public boolean verifyCaptcha(String captchaId, String captchaCode) {
        if (captchaId == null || captchaCode == null) {
            return false;
        }
        
        String key = "captcha:" + captchaId;
        String storedCode = redisTemplate.opsForValue().get(key);
        
        if (storedCode == null) {
            return false;
        }
        
        // 验证成功后删除验证码
        redisTemplate.delete(key);
        
        return storedCode.equalsIgnoreCase(captchaCode);
    }
    
    /**
     * 生成随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 将图像转换为Base64字符串
     */
    private String imageToBase64(BufferedImage image) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            log.error("图片转Base64失败", e);
            return null;
        }
    }
    
    /**
     * 生成验证码ID
     */
    private String generateCaptchaId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 验证码返回对象
     */
    public static class CaptchaVO {
        private String captchaId;
        private String captchaImage;
        
        public CaptchaVO(String captchaId, String captchaImage) {
            this.captchaId = captchaId;
            this.captchaImage = captchaImage;
        }
        
        public String getCaptchaId() {
            return captchaId;
        }
        
        public String getCaptchaImage() {
            return captchaImage;
        }
    }
}