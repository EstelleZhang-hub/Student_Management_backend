package com.example.student.dto;

import lombok.Data;

/**
 * 登录请求DTO
 */
@Data
public class LoginDTO {
    /**
     * 学号
     */
    private String studentId;
    /**
     * 密码
     */
    private String password;
}