package com.bm.jw.entity.sj.sy.Excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SyXmExcel {
    @Excel(name="实验室名")
    @Pattern(regexp ="/^.{0,30}$/",message = "实验室名必须是长度不超过30的字符串")
    @NotNull
    private String Sysm;        //实验室名

    @Excel(name="课程号")
    @Pattern(regexp ="/^.{0,30}$/",message = "实验室名必须是长度不超过30的字符串")
    @NotNull
    private String Kch;         //课程号

    @Excel(name="实验项目名称")
    @Pattern(regexp ="/^.{0,20}$/",message = "课程号必须是长度不超过20的字符串")
    private String Syxmmc;      //实验项目名称

    @Excel(name="实验项目编号")
    @Pattern(regexp ="/^.{0,20}$/",message = "实验项目编号必须是长度不超过20的字符串")
    private String Syxmbh;      //实验项目编号

    @Excel(name="实验学时")
    @Pattern(regexp ="/^[0-9]*$ /",message = "实验学时必须是数字")
    private Integer Syxs;           //实验学时

    @Excel(name="实验分室名")
    @Pattern(regexp ="/^.{0,30}$/",message = "实验分室名必须是长度不超过30的字符串")
    private String Syfsm;       //实验分室名

    @Excel(name="教工号")
    @Pattern(regexp ="/^.{0,20}$/",message = "教工号必须是长度不超过20的字符串")
    private String Jgh;         //教工号
}
