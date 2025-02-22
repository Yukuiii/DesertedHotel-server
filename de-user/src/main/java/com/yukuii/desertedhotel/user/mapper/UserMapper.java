package com.yukuii.desertedhotel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yukuii.desertedhotel.user.model.entity.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 