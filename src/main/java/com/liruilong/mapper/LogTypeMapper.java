package com.liruilong.mapper;

import com.liruilong.model.LogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogTypeMapper {

    List<LogType> selectByPrimaryKey();

    Long LogTypeAlltotal(Integer logtype);

    List<LogType> getLogTypeAll(@Param("page") Integer page,@Param("size") Integer size,@Param("logtypet") Integer logtype);

    Integer  updateLogType(LogType logType);

    Integer addLogType(LogType logType);

    Integer deleteLogType(Integer id);
}