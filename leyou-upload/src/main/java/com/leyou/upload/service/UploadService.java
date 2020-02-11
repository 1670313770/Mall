package com.leyou.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author CK
 * @create 2020-02-10-10:20
 */
@Service
@Slf4j
public class UploadService {

    private static List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg");

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //判断文件类型
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            log.info("文件名不合法："+originalFilename);
            return null;
        }
        //判断未见内容，是否为图片
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read == null){
                log.info("文件内容不合法："+originalFilename);
                return null;
            }
            //保存到服务器
            String uuid=UUID.randomUUID().toString();
            file.transferTo(new File("F:\\JavaObjIDEA\\leyou\\UpdataImages\\" +uuid+"~"+originalFilename));
            //
            return "http://image.leyou.com/"+uuid+"~"+originalFilename;
        } catch (IOException e) {
            log.info("服务器内部错误："+originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
