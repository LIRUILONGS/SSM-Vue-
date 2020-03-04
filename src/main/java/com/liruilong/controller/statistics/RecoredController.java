package com.liruilong.controller.statistics;


import com.liruilong.service.DataModelTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author Liruilong
 * @Description
 * @Date 13:51 2020/3/3
 * @Param
 * @return
 **/

@RestController
@RequestMapping("/statistics/recored")
public class RecoredController {

    @Autowired
    DataModelTService dataModelTService;






}
