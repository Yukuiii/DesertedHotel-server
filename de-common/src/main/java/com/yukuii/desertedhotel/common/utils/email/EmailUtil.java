//package com.yukuii.desertedhotel.common.utils.email;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import cn.hutool.core.util.RandomUtil;
//import jakarta.annotation.Resource;
//import jakarta.mail.internet.MimeMessage;
//import lombok.extern.slf4j.Slf4j;
//
//// 邮箱工具类
//@Slf4j
//@Component
//public class EmailUtil {
//
//    @Resource
//    private JavaMailSender mailSender;
//
//    @Resource
//    private StringRedisTemplate redisTemplate;
//
//    @Value("${spring.mail.username}")
//    private String fromEmail;
//
//    @Value("${email.code.expire:300}")
//    private Long codeExpireSeconds;
//
//    /**
//     * 发送HTML格式邮件
//     */
//    public void sendHtmlEmail(String to, String subject, String content) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(fromEmail);
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);
//            mailSender.send(message);
//            log.info("邮件发送成功: {} -> {}", fromEmail, to);
//        } catch (Exception e) {
//            log.error("邮件发送失败", e);
//            throw new RuntimeException("邮件发送失败");
//        }
//    }
//
//    /**
//     * 发送验证码邮件
//     */
//    public void sendVerificationCodeEmail(String to) {
//        String code = RandomUtil.randomNumbers(6);
//        String content = buildVerificationEmailContent(code);
//
//        // 存储到Redis，有效期5分钟
//        String key = "email:code:" + to;
//        redisTemplate.opsForValue().set(key, code, codeExpireSeconds, TimeUnit.SECONDS);
//
//        sendHtmlEmail(to, "【智慧无人酒店】验证码", content);
//    }
//
//    /**
//     * 验证邮箱验证码
//     */
//    public boolean verifyEmailCode(String email, String code) {
//        String key = "email:code:" + email;
//        String storedCode = redisTemplate.opsForValue().get(key);
//        if (storedCode == null) {
//            return false;
//        }
//        return storedCode.equals(code);
//    }
//
//    /**
//     * 构建验证码邮件内容
//     */
//    private String buildVerificationEmailContent(String code) {
//        long minutes = codeExpireSeconds / 60;
//        return "<div style='font-family:Helvetica Neue,PingFang SC,Microsoft YaHei,sans-serif;padding:20px'>"
//                + "<h2 style='color:#333'>智慧无人酒店验证码</h2>"
//                + "<p style='color:#666'>您的验证码是：</p>"
//                + "<p style='font-size:24px;color:#1890ff;margin:20px 0'>" + code + "</p>"
//                + "<p style='color:#999;font-size:12px'>此验证码" + minutes + "分钟内有效，请勿泄露给他人</p>"
//                + "</div>";
//    }
//}
