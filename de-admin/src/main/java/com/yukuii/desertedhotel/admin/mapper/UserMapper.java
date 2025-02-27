package com.yukuii.desertedhotel.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yukuii.desertedhotel.admin.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 