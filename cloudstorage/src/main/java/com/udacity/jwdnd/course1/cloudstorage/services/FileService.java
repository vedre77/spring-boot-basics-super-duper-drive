package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    private UserMapper userMapper;
    private FileMapper fileMapper;

    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public void saveFile(MultipartFile multipartFile, String userName) throws IOException {

        InputStream fis = multipartFile.getInputStream();
        // file processing:
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray();
        //
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(userName).getUserid();
        FileModel file = new FileModel(fileName, contentType, fileSize, userId, fileData);
        fileMapper.insertFile(file);
    }

    public String[] getFileNameList() {
        return fileMapper.getFileNameList();
    }

    public FileModel getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }
}
