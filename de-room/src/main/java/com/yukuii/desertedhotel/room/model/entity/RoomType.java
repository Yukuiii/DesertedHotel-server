package com.yukuii.desertedhotel.room.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("room_type")
@Schema(description = "房型实体")
public class RoomType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "房型ID")
    private Long id;

    @Schema(description = "房型名称")
    private String name;

    @Schema(description = "房间价格")
    private BigDecimal price;

    @Schema(description = "是否有窗(0:无 1:有)")
    private Integer hasWindow;

    @Schema(description = "床型(1:单床 2:双床 3:大床)")
    private Integer bedType;

    @Schema(description = "可住人数")
    private Integer maxGuests;

    @Schema(description = "房间面积(平方米)")
    private Integer area;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @TableLogic
    @Schema(description = "是否删除")
    private Integer deleted;
} 