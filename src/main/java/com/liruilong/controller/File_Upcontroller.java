package com.liruilong.controller;


import com.liruilong.model.RespBean;
import com.liruilong.model.RespPageBean;
import com.liruilong.service.File_UpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/3/2 19:26
 */
@RestController
@RequestMapping("/system/file")
public class File_Upcontroller {


    @Autowired
    File_UpService file_upService;

    @PostMapping("/in")
    public RespBean onFileUp(MultipartFile file) throws IOException {

        if (file_upService.upFile(file)) {

            return RespBean.ok("上传成功");
        } else {
            return RespBean.error("上传失败");
        }

    }

    @GetMapping("/")
    public RespPageBean getAllFiles(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return file_upService.getAllFiles(page, size);
    }

    @GetMapping("/out")
    public ResponseEntity<byte[]> exportData(Integer id) throws IOException {
        return file_upService.exportData(id);
    }

  
}

