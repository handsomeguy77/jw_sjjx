package com.bm.jw.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * 这对应数据库中的SYS_USER表
 *
 * */
@Data
public class SysUser implements Serializable {
    private Integer Id;
    private String Username;   //用户名
    private String Jsh;         //教师号
    private String Xm;          //姓名
    private String Xym;         //学院名
    private String Sysm;        //实验室名
    private String Password;   //密码
    private String Role;   //角色
}
