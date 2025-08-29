package com.invest.khumbu.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {

    // Save file to disk and return its path as a String
    public String save(MultipartFile file, Object owner) {
        if (file != null && !file.isEmpty()) {
            try {
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = Paths.get("uploads/" + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                return path.toString(); // Return file path for saving in DB
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
