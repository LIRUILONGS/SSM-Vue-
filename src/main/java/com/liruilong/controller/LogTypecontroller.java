package com.liruilong.controller;

import com.liruilong.model.LogType;
import com.liruilong.model.OpLog;
import com.liruilong.model.RespBean;
import com.liruilong.model.RespPageBean;
import com.liruilong.service.LogTypeService;
import com.liruilong.service.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/3/2 10:14
 */
@RestController
@RequestMapping("/system/logtype")
public class LogTypecontroller {
    @Autowired
    LogTypeService logTypeService;
    
    @GetMapping("/")
    public RespPageBean getLogTypeAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, Integer logtype){
       return logTypeService.getLogTypeAll(page,size,logtype);
    }
    
    @PutMapping("/")
    public RespBean updateLogType(@RequestBody LogType logType){
        if ( logTypeService.updateLogType(logType) == 1){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.ok("更新失败");
        }
    }

    @PostMapping("/")
    public RespBean addLogType(@RequestBody LogType logType){
       
        if ( logTypeService.addLogType(logType) == 1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.ok("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteLogType(@PathVariable Integer id){
        if (logTypeService.deleteLogType(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }


}
