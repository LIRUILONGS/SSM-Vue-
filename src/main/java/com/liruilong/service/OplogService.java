package com.liruilong.service;


import com.liruilong.mapper.OpLogMapper;
import com.liruilong.model.OpLog;
import com.liruilong.model.RespPageBean;
import com.liruilong.model.datas.DataModel;
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
public class OplogService {

    @Autowired
    OpLogMapper opLogMapper;

    public RespPageBean getAllsOplog(Integer page, Integer size, Integer logtype, Date[] beginDateScope ) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        Long total = opLogMapper.selectByPrimaryKey( logtype,beginDateScope);
       // opLogMapper.getAllsOplog(page, size, logtype,beginDateScope).stream().forEach( System.out::println);
        return  new RespPageBean(total, opLogMapper.getAllsOplog(page, size, logtype,beginDateScope));
    }

    public int addOpLog(OpLog opLog){
        opLog.setAdddate(new Date());
     return    opLogMapper.insert(opLog);

    }

    public Integer updateOplog(OpLog opLog) {

        return  opLogMapper.updateByPrimaryKeySelective(opLog);
    }

    public Integer addOplog(OpLog opLog) {
        opLog.setAdddate(new Date());
        return opLogMapper.insert(opLog);
    }

    public Integer deleteOplog(Integer id) {
        return opLogMapper.deleteByPrimaryKey(id);
    }

    public Integer addopls(List<OpLog> list) {
        return opLogMapper.addopls(list);
    }

    public List<DataModel> dataViewOplogHr() {
        return opLogMapper.dataViewOplogHr();
    }
}
