package com.westar.wab.controller;

import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public GridFSFile upload(@RequestParam("files") MultipartFile file) throws IOException{
        GridFSFile fileInfo = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType());
        return fileInfo;
    }
}
