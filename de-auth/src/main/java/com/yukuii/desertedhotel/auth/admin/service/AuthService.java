package com.yukuii.desertedhotel.auth.admin.service;

import com.yukuii.desertedhotel.auth.admin.dto.LoginDTO;

import cn.dev33.satoken.stp.SaTokenInfo;

public interface AuthService {

    SaTokenInfo adminLogin(LoginDTO loginDTO);

    void logout();

}
