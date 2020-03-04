package com.liruilong.mapper;

import com.liruilong.model.OpLog;
import com.liruilong.model.datas.DataModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OpLogMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(OpLog opLog);

    Long selectByPrimaryKey(@Param("logtype") Integer logtype, @Param("beginDateScope") Date[] beginDateScope);

    int updateByPrimaryKeySelective(OpLog record);

    List<OpLog>  getAllsOplog(@Param("page") Integer page, @Param("size") Integer size, @Param("logtype") Integer logtype, @Param("beginDateScope") Date[] beginDateScope);

    Integer  addopls(List<OpLog> list);

    List<DataModel> dataViewOplogHr();
}