package com.liruilong.controller.statistics;

import com.liruilong.model.datas.DataModel;
import com.liruilong.service.File_UpService;
import com.liruilong.service.OplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Liruilong
 * @Description
 * @Date 11:21 2020/3/3
 * @Param
 * @return
 **/

@RestController
@RequestMapping("/statistics/personnel")
public class PersonnelController {

    @Autowired
    File_UpService file_upService;
    @Autowired
    OplogService oplogService;



    @Scheduled(cron = "0  */30 * * * ?")
    @GetMapping("/filetype")
    public List<DataModel> dataViewFileType(){
        return file_upService.dataViewFileType();

    }
    @Scheduled(cron = "0  */30 * * * ?")
    @GetMapping("/fileDate")
    public List<DataModel> dataFileDate(){
        return file_upService.dataFileDate();
    }
    @Scheduled(cron = "0  */30 * * * ?")
    @GetMapping("/oploghr")
    public List<DataModel> dataViewOplogHr(){
        return oplogService.dataViewOplogHr();
    }








}
