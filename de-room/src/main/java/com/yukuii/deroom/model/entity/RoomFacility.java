package com.yukuii.deroom.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("room_facility")
@Schema(description = "房间设施关联实体")
public class RoomFacility implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "房间ID")
    private Long roomId;

    @Schema(description = "设施ID")
    private Long facilityId;
} 