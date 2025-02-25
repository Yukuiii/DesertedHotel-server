package com.yukuii.deroom.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yukuii.deroom.model.entity.Room;

public interface RoomService {

    /**
     * 保存房间信息
     */ 
    Integer saveRoom(Room room);

    /**
     * 根据ID获取房间信息
     */
    Room getRoomById(Long id);

    /**
     * 获取所有房间信息
     */
    List<Room> getAllRooms();

    /**
     * 更新房间信息
     */
    Integer updateRoom(Room room);

    /**
     * 删除房间信息 
     */
    Integer deleteRoom(Long id);

    /**
     * 分页获取所有房间信息
     */
    PageInfo<Room> getAllRooms(Integer pageNum, Integer pageSize);
    
}
