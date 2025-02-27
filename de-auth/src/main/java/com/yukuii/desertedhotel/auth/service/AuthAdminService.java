package com.yukuii.desertedhotel.auth.service;

import com.yukuii.desertedhotel.auth.model.dto.AdminLoginDTO;

import cn.dev33.satoken.stp.SaTokenInfo;

public interface AuthAdminService {

    SaTokenInfo adminLogin(AdminLoginDTO adminLoginDTO);

    void logout();

}
