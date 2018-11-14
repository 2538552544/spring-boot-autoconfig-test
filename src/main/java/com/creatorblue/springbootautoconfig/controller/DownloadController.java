package com.creatorblue.springbootautoconfig.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController {
    @RequestMapping("download")
    public String downLoad(HttpServletResponse response) throws IOException {
        String fileName = "test.png";
        String path = "D:/IOTest";
        File file = new File(path + "/" + fileName);
        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            FileInputStream fis = null;
            byte[] bytes = new byte[1024];
            OutputStream outputStream = null;
            BufferedInputStream bufferedInputStream = null;
            try {
                outputStream = response.getOutputStream();
                fis = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fis);
                int i = bufferedInputStream.read(bytes);
                while (i != -1) {
                    outputStream.write(bytes);
                    i = bufferedInputStream.read(bytes);
                }
            } catch (Exception e) {

            } finally {
                bufferedInputStream.close();
                fis.close();
            }
        }
        return null;
    }

    @GetMapping("downloadNew")
    public ResponseEntity<byte[]> downLoadNews() throws IOException {
        String path = "D:/IOTest";
        String fileName = "test.png";
        File file = new File(path + "/" + fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        System.out.println("访问方法");
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
    }
}
