package com.yukuii.desertedhotel.room.controller;

import com.yukuii.deroom.dto.request.RoomPriceUpdateRequest;
import com.yukuii.deroom.dto.request.PricingStrategyRequest;
import com.yukuii.deroom.service.RoomPriceService;
import com.yukuii.deroom.dto.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间价格管理")
@RestController
@RequestMapping("/room/price")
@RequiredArgsConstructor
public class RoomPriceController {
    private final RoomPriceService priceService;

    @Operation(summary = "批量更新价格")
    @PutMapping("/batch")
    public CommonResult<Integer> batchUpdatePrices(
        @Valid @RequestBody List<RoomPriceUpdateRequest> requests) {
        return CommonResult.success(priceService.batchUpdatePrices(requests));
    }

    @Operation(summary = "设置动态定价规则")
    @PostMapping("/strategy")
    public CommonResult<Long> setPricingStrategy(
        @Valid @RequestBody PricingStrategyRequest request) {
        return CommonResult.success(priceService.createStrategy(request));
    }
} 