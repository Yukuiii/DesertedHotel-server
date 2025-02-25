package com.yukuii.deroom.controller;

import com.yukuii.deroom.dto.CommonResult;
import com.yukuii.deroom.dto.PriceTrendResponse;
import com.yukuii.deroom.service.RoomReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "房间数据分析")
@RestController
@RequestMapping("/room/report")
@RequiredArgsConstructor
public class RoomReportController {
    private final RoomReportService reportService;

    @Operation(summary = "房型分布统计")
    @GetMapping("/type-distribution")
    public CommonResult<Map<String, Long>> getTypeDistribution() {
        return CommonResult.success(reportService.getRoomTypeStats());
    }

    @Operation(summary = "价格趋势分析")
    @GetMapping("/price-trend")
    public CommonResult<List<PriceTrendResponse>> analyzePriceTrend(...)
} 