package com.liruilong.service;

import com.liruilong.mapper.LogTypeMapper;
import com.liruilong.model.LogType;
import com.liruilong.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Liruilong 
 * @Description 
 * @Date 20:27 2020/3/1
 * @Param  
 * @return  
 **/

@Service
public class LogTypeService {

    @Autowired
    LogTypeMapper logTypeMapper;


    public List<LogType> getAllsLogtype() {
        return logTypeMapper.selectByPrimaryKey();
    }
    public RespPageBean getLogTypeAll(Integer page, Integer size, Integer logtype) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        Long total = logTypeMapper.LogTypeAlltotal(logtype);
        return  new RespPageBean(total, logTypeMapper.getLogTypeAll(page, size, logtype));
    }

    public Integer updateLogType(LogType logType) {
        return logTypeMapper.updateLogType(logType);
    }

    public Integer addLogType(LogType logType) {
        logType.setAdddate(new Date());
        return logTypeMapper.addLogType(logType);
    }

    public Integer deleteLogType(Integer id) {
        return logTypeMapper.deleteLogType(id);
    }
}
