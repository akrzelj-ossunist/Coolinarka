package oss.akrzelj.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class SaveImage {

    public void save(String uploadDir, String fileName, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = file.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            System.out.println("Image path: " + filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Could not save image file: " + fileName, ioe);
        }

    }
}
