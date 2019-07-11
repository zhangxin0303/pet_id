package com.cqcej.web.modules.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEntity {
    private String token;
    private int expire;
}
