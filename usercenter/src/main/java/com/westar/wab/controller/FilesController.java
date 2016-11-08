package com.westar.wab.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @Value("${file.download.customize.types}")
    private String imgTypes;

    /**
     * 上传文件
     * @param file 要上传的文件
     * @return  Json字符串包含文件信息
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException{
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

    /**
     * 文件下载
     * @param id    要下载的文件ID
     * @param h 下载后压缩高度（暂未支持）
     * @param w 下载后的压缩宽度（暂未支持）
     * @param response  http返回试听对象
     * @throws IOException
     */
    @RequestMapping(value = "/download/{id}")
    public void download(@PathVariable("id") String id,@RequestParam(value = "h", required = false) Integer h,@RequestParam(value = "w", required = false) Integer w, HttpServletResponse response) throws IOException {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))));
        if (file != null) {
            response.setContentType(file.getContentType());
            response.setContentLength((int)file.getLength());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
            response.setHeader("Cache-Control","no-cache");
            response.setDateHeader("Expires", 0);

//            String type = file.getFilename().substring(file.getFilename().indexOf(".") + 1);
//            List<String> typeList = Arrays.asList(imgTypes.toLowerCase().split(","));
//            if (typeList.contains(type.toLowerCase())) {
//                if ( h != null && h<=300 && w != null && w<=300){
//                    Image bufferedImage = ImageIO.read(file.getInputStream());
//
//                    Image image = bufferedImage.getScaledInstance(w, h,Image.SCALE_DEFAULT);
//                    BufferedImage outputImage = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
//
//                    Graphics graphics = outputImage.getGraphics();
//                    graphics.drawImage(image, 0, 0, w, h, null); // 绘制缩小后的图
//                    graphics.dispose();
//                    ImageIO.write(outputImage, "JPEG", response.getOutputStream());
//                } else {
//                    file.writeTo(response.getOutputStream());
//                }
//            } else {
                file.writeTo(response.getOutputStream());
//            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not fond, id " + id);
        }
    }
}
