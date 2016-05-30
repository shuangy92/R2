package com.worksap.stm2016.controller;

import com.worksap.stm2016.repository.FileProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import static org.springframework.util.StringUtils.getFilenameExtension;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    FileProfileRepository fileProfileRepository;

    @RequestMapping(value = "files/{id}", method = RequestMethod.GET)
    //@ResponseBody
    public void getFile(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        String path = fileProfileRepository.findOne(id).getPath();

        FileSystemResource fileSystemResource = new FileSystemResource(path);
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileSystemResource.getInputStream());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);

        int bytesRead = 0;
        byte[] buffer = new byte[1024];

        String extension = getFilenameExtension(path);
        switch (extension) {
            case "pdf":
                response.setContentType("application/pdf");
                break;
            case "doc":
                response.setContentType("application/msword");
                break;
            case "docx":
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                break;
            case "jpg":
            case "jpeg":
                response.setContentType("image/jpeg");
                break;
            case "png":
                response.setContentType("image/png");
                break;
            case "gif":
                response.setContentType("image/gif");
                break;
            default:
                response.setContentType("text/plain");
                break;
        }

        response.setHeader("Content-Disposition", "inline;");
        response.setHeader("Cache-Control", "cache, must-revalidate");
        response.setHeader("Content-Disposition", "inline; filename=" + fileSystemResource.getFilename());

        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, bytesRead);
        }

        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}