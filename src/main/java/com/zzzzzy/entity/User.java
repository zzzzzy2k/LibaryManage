package com.zzzzzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/7 22:00
 * @Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int userId;
    private String username;
    private String password;
}
