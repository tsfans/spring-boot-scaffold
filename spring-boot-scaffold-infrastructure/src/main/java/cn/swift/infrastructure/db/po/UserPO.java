package cn.swift.infrastructure.db.po;

import lombok.Data;

@Data
public class UserPO {
    
    private Long id;

    private String username;

    private String password;

    private Integer version;
    
}