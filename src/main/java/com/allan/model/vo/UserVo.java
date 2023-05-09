package com.allan.model.vo;

import lombok.Data;

@Data
public class UserVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * token令牌
     */
    private String token;
}
