package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.UploadFileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    private static String UPLOAD_DIR = "uploads";

    @PostMapping("/upload-file")
    private ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
    HttpServletRequest request) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath =
                    request.getServletContext().getContextPath() + UPLOAD_DIR + File.separator + fileName;
            saveFile(file.getInputStream(), filePath);
            return new ResponseEntity<>(new UploadFileResponse(fileName, filePath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void saveFile(InputStream inputStream, String filePath) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(filePath));

            int read = 0;
            byte []bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        }
    }
}
