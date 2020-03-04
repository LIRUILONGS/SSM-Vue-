package com.liruilong.service;

import com.liruilong.mapper.FileUpMapper;
import com.liruilong.model.FileUp;
import com.liruilong.model.RespPageBean;
import com.liruilong.model.datas.DataModel;
import com.liruilong.service.utils.FileSizeUtil;
import com.liruilong.service.utils.FileTypeUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/3/2 19:29
 */
@Service
@Transactional
public class File_UpService {


    @Autowired
    FileUpMapper file_upMapper;
   Logger logger = Logger.getLogger("com.liruilong.service.File_UpService");


    public boolean upFile(MultipartFile file ) throws IOException {
        if (file.isEmpty()){
            return false;
        }
        String fileName = file.getOriginalFilename();
        String filenamefix = file.getOriginalFilename().substring(fileName.lastIndexOf("."));
        String newFile = UUID.randomUUID().toString() + filenamefix;
        String filePath = "F:\\Demo1\\download\\";
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(new File(filePath+newFile)));
        FileUp fileUp = new FileUp(fileName,filePath+newFile,FileSizeUtil.formatFileSize(file.getSize()),FileTypeUtil.getType(filenamefix),DateFormat.getDateTimeInstance().format(new Date()));
        System.out.println(fileUp.toString());

        return true && file_upMapper.addFile(fileUp)==1;
    }

    public RespPageBean getAllFiles(Integer page, Integer size) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        Long total = file_upMapper.selectByPrimaryKey( );
        // opLogMapper.getAllsOplog(page, size, logtype,beginDateScope).stream().forEach( System.out::println);
        return  new RespPageBean(total, file_upMapper.getAllFiles(page, size ));
    }


    public ResponseEntity<byte[]> exportData(Integer id) throws IOException {
        FileUp fileUp = file_upMapper.selectByKey(id);
        //处理中文编码
        String myFileName=new String(fileUp.getFileName().getBytes("utf-8"),"iso-8859-1");
        //设置头信息
        HttpHeaders headers=new HttpHeaders();
        //设置下载的附件 (myFileName必须处理中文名称哦!)
        headers.setContentDispositionFormData("attachment", myFileName);
        //设置MIME类型
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //用FileUpload组件的FileUtils读取文件，并构建成ResponseEntity<byte[]>返回给浏览器
        //HttpStatus.CREATED是HTTP的状态码201
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(fileUp.getFilePath())),headers, HttpStatus.CREATED);
    }

    public List<DataModel> dataViewFileType() {
      return   file_upMapper.dataViewFileType();
    }

    public List<DataModel> dataFileDate() {
        return file_upMapper.dataFileDate();
    }
}
