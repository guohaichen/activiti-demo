package com.example.activiti.demo.web.controller;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SqlMapper {


    @Select("select * from ACT_HI_VARINST limit 1")
    String testSqlLog();
}
