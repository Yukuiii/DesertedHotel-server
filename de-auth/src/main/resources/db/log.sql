-- 管理员日志表
CREATE TABLE auth_admin_log (
    id BIGINT PRIMARY KEY COMMENT '日志ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    username VARCHAR(255) NOT NULL COMMENT '用户名',
    operating_ip VARCHAR(45) COMMENT '操作IP',
    browser_type VARCHAR(255) COMMENT '浏览器类型',
    operating_system VARCHAR(255) COMMENT '操作系统',
    status TINYINT NOT NULL COMMENT '日志状态（0失败 1成功）',
    message TEXT COMMENT '日志信息',
    type TINYINT NOT NULL COMMENT '日志类型（0登录 1登出）',
    log_time DATETIME NOT NULL COMMENT '日志时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员日志';

-- 用户日志表
CREATE TABLE auth_user_log (
    id BIGINT PRIMARY KEY COMMENT '日志ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    username VARCHAR(255) NOT NULL COMMENT '用户名',
    operating_ip VARCHAR(45) COMMENT '操作IP',
    browser_type VARCHAR(255) COMMENT '浏览器类型',
    operating_system VARCHAR(255) COMMENT '操作系统',
    status TINYINT NOT NULL COMMENT '状态（0失败 1成功）',
    message TEXT COMMENT '日志信息',
    type TINYINT NOT NULL COMMENT '日志类型（0登录 1登出）',
    log_time DATETIME NOT NULL COMMENT '日志时间',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户日志';

-- 索引配置
ALTER TABLE auth_admin_log ADD INDEX idx_admin_user (user_id);
ALTER TABLE auth_admin_log ADD INDEX idx_admin_log_time (log_time);
ALTER TABLE auth_admin_log ADD INDEX idx_admin_status (status);

ALTER TABLE auth_user_log ADD INDEX idx_user (user_id);
ALTER TABLE auth_user_log ADD INDEX idx_log_time (log_time);
ALTER TABLE auth_user_log ADD INDEX idx_status (status);
