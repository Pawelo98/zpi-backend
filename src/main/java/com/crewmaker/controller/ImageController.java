package com.crewmaker.controller;

import com.crewmaker.entity.User;
import com.crewmaker.entity.UserProfileImage;
import com.crewmaker.exception.AppException;
import com.crewmaker.exception.ResourceNotFoundException;
import com.crewmaker.repository.UserProfileImageRepository;
import com.crewmaker.repository.UserRepository;
import com.crewmaker.reqbody.ApiResponse;
import com.crewmaker.reqbody.ImageResponse;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
@Controller
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileImageRepository userProfileImageRepository;

    @GetMapping("/usersProfileImage/{username}")
    public ResponseEntity<ImageResponse> getUserProfileImage(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return Optional.ofNullable(user.getUserProfileImage()).map(retrivedImage -> ResponseEntity
                .ok()
                .body(new ImageResponse(scale(decompressBytes(retrivedImage.getBinaryData()),256,256),retrivedImage.getName()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usersProfileImageSmall/{username}")
    public ResponseEntity<ImageResponse> getUserProfileImageSmall(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return Optional.ofNullable(user.getUserProfileImage()).map(retrivedImage -> ResponseEntity
                .ok()
                .body(new ImageResponse(scale(decompressBytes(retrivedImage.getBinaryData()),100,100),retrivedImage.getName()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping("/uploadPhoto/")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfileImage newImg = new UserProfileImage(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));

        Optional<UserProfileImage> currentImg = Optional.ofNullable(user.getUserProfileImage());
        if(currentImg.isPresent()){
            userProfileImageRepository.setUserProfileImageById(file.getOriginalFilename(), file.getContentType(),
                    compressBytes(file.getBytes()), currentImg.get().getImageId());
        }else{
            user.setUserProfileImage(newImg);
            userRepository.save(user);
        }

        return ResponseEntity.ok(new ApiResponse(true, "Image uploaded successfully"));
    }

    public byte[] scale(byte[] fileData, int height, int width) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);

            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "png", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new AppException("IOException in scale");
        }
    }

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
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
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
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
