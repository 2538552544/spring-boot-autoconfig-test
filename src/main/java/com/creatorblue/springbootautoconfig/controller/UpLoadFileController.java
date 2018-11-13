package com.creatorblue.springbootautoconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UpLoadFileController {
    @PostMapping("uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()){
            return "文件不能为空";
        }
        String fileName = file.getOriginalFilename();
        //获取文件的大小
        int size = (int) file.getSize();
        System.out.println(size);
        String path = "F:/IOTest";
        File desc = new File(path+"/"+fileName);
        //如果夫目录不存在 则创建
        if(desc.getParentFile().exists()){
            desc.getParentFile().mkdir();
        }
        try {
            file.transferTo(desc);
            return "true";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    @GetMapping("uploadFile")
    public String uploadFileHtml() {
        return "upload";
    }
}
