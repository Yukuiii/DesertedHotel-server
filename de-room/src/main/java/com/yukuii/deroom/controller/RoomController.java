package com.yukuii.deroom.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yukuii.deroom.model.dto.RoomRequest;
import com.yukuii.deroom.model.dto.RoomResponse;
import com.yukuii.deroom.service.RoomService;
import com.yukuii.desertedhotel.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yukuii.deroom.model.entity.Room;

@Tag(name = "房间管理")
@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "分页查询房间列表")
    @GetMapping
    public CommonResult<PageInfo<Room>> getAllRooms(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return CommonResult.success(roomService.getAllRooms(page, size));
    }

    @Operation(summary = "根据ID获取房间详情")
    @GetMapping("/{id}")
    public CommonResult<Room> getRoomById(@PathVariable Long id) {
        return CommonResult.success(roomService.getRoomById(id));
    }

    @Operation(summary = "创建房间")
    @PostMapping
    public CommonResult<Long> createRoom(@Valid @RequestBody RoomRequest request) {
        return CommonResult.success(roomService.createRoom(request));
    }


    @Operation(summary = "删除房间")
    @DeleteMapping("/{id}")
    public CommonResult<Integer> deleteRoom(@PathVariable Long id) {
        return CommonResult.success(roomService.deleteRoom(id));
    }

    @Operation(summary = "复合条件搜索")
    @GetMapping("/search")
    public CommonResult<List<Room>> advancedSearch(
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer minCapacity) {
        return CommonResult.success(roomService.advancedSearch(roomType, minPrice, maxPrice, minCapacity));
    }

    @Operation(summary = "推荐相似房间")
    @GetMapping("/{id}/similar")
    public CommonResult<List<Room>> findSimilarRooms(
            @PathVariable Long id,
            @RequestParam(defaultValue = "3") int count) {
        return CommonResult.success(roomService.findSimilarRooms(id, count));
    }
}
