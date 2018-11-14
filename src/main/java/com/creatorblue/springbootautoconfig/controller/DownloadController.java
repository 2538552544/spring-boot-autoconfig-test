package com.creatorblue.springbootautoconfig.controller;

import org.springframework.stereotype.Controller;
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
}
