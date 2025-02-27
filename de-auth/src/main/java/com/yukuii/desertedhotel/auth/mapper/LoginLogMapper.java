package com.yukuii.desertedhotel.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yukuii.desertedhotel.auth.model.entity.LoginLog;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
} 