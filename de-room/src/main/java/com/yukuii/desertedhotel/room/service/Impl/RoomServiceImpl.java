package com.yukuii.desertedhotel.room.service.Impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.yukuii.desertedhotel.room.model.entity.Room;
import com.yukuii.desertedhotel.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import com.yukuii.desertedhotel.room.mapper.RoomMapper;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Integer saveRoom(Room room) {
        return roomMapper.insert(room);
    }
    @Override
    public Room getRoomById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID不能为空");
        }
        return roomMapper.selectById(id);

    }

    @Override
    public List<Room> getAllRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRooms'");
    }

    @Override
    public Integer updateRoom(Room room) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRoom'");
    }

    @Override
    public Integer deleteRoom(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRoom'");
    }

    @Override
    public PageInfo<Room> getAllRooms(Integer pageNum, Integer pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllRooms'");
    }


}
