package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

    @Select("SELECT filename FROM files where filename = #{filename}")
    boolean getFileName(String fileName);

    @Select("SELECT * FROM files where filename = #{fileName}")
    FileModel getFile(String fileName);

    @Select("SELECT filename FROM files")
    String[] getFileNameList();

    @Insert("INSERT INTO files (filename, contenttype, filesize, userid, filedata)" +
            "VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    void insertFile(FileModel file);
}
