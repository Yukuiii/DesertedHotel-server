package com.yukuii.desertedhotel.auth.service;

import com.yukuii.desertedhotel.auth.model.dto.UserLoginDTO;

import cn.dev33.satoken.stp.SaTokenInfo;

public interface AuthUserService {

    void logout();

    SaTokenInfo userLogin(UserLoginDTO userLoginDTO);
}
