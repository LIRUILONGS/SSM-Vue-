package com.liruilong.controller;

import com.liruilong.model.LogType;
import com.liruilong.model.OpLog;
import com.liruilong.model.RespBean;
import com.liruilong.model.RespPageBean;
import com.liruilong.service.LogTypeService;
import com.liruilong.service.OplogService;
import com.liruilong.service.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author Liruilong
 * @Description
 * @Date 20:27 2020/3/1
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/system/log")
public class OplogController {

    @Autowired
    OplogService oplogService;

    @Autowired
    LogTypeService logTypeService;

    @GetMapping("/")
    public RespPageBean getAllsOplog(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "12") Integer size, Integer logtype, Date[] beginDateScope) {
        // oplogService.getAllsOplog(page, size, logtype,beginDateScope ).getData().stream().forEach(System.err::println);

        return oplogService.getAllsOplog(page, size, logtype, beginDateScope);
    }


    @GetMapping("/logtype")
    public List<LogType> getAllsLogtype() {
        return logTypeService.getAllsLogtype();
    }

    @PutMapping("/")
    public RespBean updateOplog(@RequestBody OpLog opLog) {

        if (oplogService.updateOplog(opLog) == 1) {
            return RespBean.ok("更新成功");
        } else {
            return RespBean.ok("更新失败");
        }
    }

    @PostMapping("/")
    public RespBean addOplog(@RequestBody OpLog opLog) {
        System.out.println(opLog.toString());
        if (oplogService.addOplog(opLog) == 1) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.ok("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deleteOplog(@PathVariable Integer id) {
        if (oplogService.deleteOplog(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @GetMapping("/out")
    public ResponseEntity<byte[]> exportData() {

        List<OpLog> list = (List<OpLog>) oplogService.getAllsOplog(null, null, null, null).getData();
      //  list.stream().forEach(System.out::println);

        return POIUtils.OpLog2Excel(list);
    }

    @PostMapping("/in")
    public RespBean importData(MultipartFile file) throws IOException {

        List<OpLog> list = POIUtils.excel2Oplog(file, logTypeService.getAllsLogtype());
        list.stream().forEach(System.err::println);

        if (oplogService.addopls(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
            return RespBean.error("上传失败");
    }

}
