package com.yukuii.deroom.controller;

import com.yukuii.deroom.dto.request.RoomStatusUpdateRequest;
import com.yukuii.deroom.dto.response.CommonResult;
import com.yukuii.deroom.service.RoomStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Tag(name = "房间状态管理")
@RestController
@RequestMapping("/room/status")
@RequiredArgsConstructor
public class RoomStatusController {
    private final RoomStatusService statusService;

    @Operation(summary = "更新单个房态")
    @PatchMapping("/{id}")
    public CommonResult<Boolean> updateStatus(
        @PathVariable Long id,
        @RequestParam @NotBlank String status) {
        return CommonResult.success(statusService.updateStatus(id, status));
    }

    @Operation(summary = "批量房态更新")
    @PutMapping("/batch")
    public CommonResult<Integer> batchUpdateStatus(
        @Valid @RequestBody List<RoomStatusUpdateRequest> requests) {
        return CommonResult.success(statusService.batchUpdate(requests));
    }

    

} 