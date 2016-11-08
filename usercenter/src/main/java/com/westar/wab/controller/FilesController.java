package com.westar.wab.controller;

import com.mongodb.gridfs.GridFSFile;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * FilesController
 *
 * @author Anbang Wang
 * @date 2016/11/7
 */
@Controller
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException{
        System.out.println(file.getSize());
        GridFSFile fileInfo = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("filename", fileInfo.getFilename());
        jsonObject.put("aliases", fileInfo.getAliases());
        jsonObject.put("uploadDate", fileInfo.getUploadDate().getTime());
        jsonObject.put("id", fileInfo.getId().toString());
        jsonObject.put("contentType", fileInfo.getContentType());
        jsonObject.put("md5", fileInfo.getMD5());
        jsonObject.put("chunkSize", fileInfo.getChunkSize());
        jsonObject.put("length", fileInfo.getLength());
        return jsonObject.toString();
    }
}
