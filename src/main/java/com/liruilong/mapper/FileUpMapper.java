package com.liruilong.mapper;



import com.liruilong.model.FileUp;
import com.liruilong.model.OpLog;
import com.liruilong.model.datas.DataModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Description :
 * @Author: Liruilong
 * @Date: 2020/3/2 19:34
 */
@Mapper
public interface FileUpMapper {

    Integer  addFile(FileUp var1) ;

    FileUp selectByKey(Integer fileld);

    Long selectByPrimaryKey();

    List<FileUp> getAllFiles(@Param("page") Integer page, @Param("size") Integer size);

    List<DataModel>  dataViewFileType();

    List<DataModel> dataFileDate();
}
