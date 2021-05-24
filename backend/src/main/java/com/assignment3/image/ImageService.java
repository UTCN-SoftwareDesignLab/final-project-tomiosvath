package com.assignment3.image;

import com.assignment3.image.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image findById(Long id){
        return imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No image with id: " + id));
    }

    public void delete(Long id){
        imageRepository.delete(findById(id));
    }

    public Image upload(MultipartFile file){
        try {
            Image img = new Image(file.getOriginalFilename(), file.getContentType(),
                    compressBytes(file.getBytes()));
            return imageRepository.save(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public Image getImage(Long id) throws IOException {
        Image retrievedImage = findById(id);
        return new Image(retrievedImage.getName(), retrievedImage.getType(),
                decompressBytes(retrievedImage.getPicByte()));
    }*/

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }


}
