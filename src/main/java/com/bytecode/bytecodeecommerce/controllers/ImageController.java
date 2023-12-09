package com.bytecode.bytecodeecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.exception.SdkException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@RestController
@RequestMapping("/api")
public class ImageController {

    private final S3Client s3Client;

    @Autowired
    public ImageController(S3Client s3Client) {
        this.s3Client = s3Client;
    }
    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> handleImageUpload(
            @RequestParam("file") MultipartFile imageFile) {
        try {
            // Verifica que el archivo no sea nulo
            if (imageFile == null || imageFile.isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Image file is required"));
            }

            String randomId = UUID.randomUUID().toString();
            String bucketName = "aws-bytecode";
            String fileExtension = getFileExtension(imageFile.getOriginalFilename());
            String key = "path/in/s3/" + randomId + "." + fileExtension;

            // Subir el archivo a S3
            PutObjectResponse response = s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType("image/" + fileExtension)
                    .key(key)
                    .build(), software.amazon.awssdk.core.sync.RequestBody.fromInputStream(imageFile.getInputStream(), imageFile.getSize()));

            // Construir la URL manualmente
            URL imageUrl = new URL("https://" + bucketName + ".s3.amazonaws.com/" + key);

            // Construir el JSON de respuesta
            Map<String, String> responseData = new HashMap<>();
            responseData.put("message", "Image uploaded successfully");
            responseData.put("url", imageUrl.toString());

            return ResponseEntity.ok(responseData);
        } catch (SdkException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Error uploading image to S3: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Error constructing image URL: " + e.getMessage()));
        }
    } private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return ""; // O maneja el caso en el que no hay una extensión
    }

    @DeleteMapping("/image/{imageKey}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageKey) {
        try {
            // Nombre del bucket de S3
            String bucketName = "aws-bytecode";
            // Extraer la clave del objeto de la URL
            // Extraer la clave del objeto de la URL
            String objectKey = "path/in/s3/" + imageKey;

            // Construir la solicitud para eliminar el objeto en S3
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket( bucketName)
                    .key(objectKey)
                    .build();

            // Enviar la solicitud de eliminación
            DeleteObjectResponse deleteObjectResponse = s3Client.deleteObject(deleteObjectRequest);

            // Verificar la respuesta de eliminación
            if (deleteObjectResponse.sdkHttpResponse().isSuccessful()) {
                return ResponseEntity.ok("Imagen eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al eliminar la imagen de S3");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar la imagen");
        }
    }
    /*@PostMapping("/upload")
    public ResponseEntity<Map<String, String>> handleImageUpload(@RequestBody byte[] imageBytes) {
        try {
            String randomId = UUID.randomUUID().toString();
            String bucketName = "aws-bytecode";
            String key = "path/in/s3/" + randomId + ".jpg";

            // Subir el arreglo de bytes a S3
            assert imageBytes != null;
            PutObjectResponse response = s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType("image/jpeg")
                    .key(key)
                    .build(), software.amazon.awssdk.core.sync.RequestBody.fromBytes(imageBytes));

            // Construir la URL manualmente
            URL imageUrl = new URL("https://" + bucketName + ".s3.amazonaws.com/" + key);

            // Construir el JSON de respuesta
            Map<String, String> responseData = new HashMap<>();
            responseData.put("message", "Image uploaded successfully");
            responseData.put("url", imageUrl.toString());

            return ResponseEntity.ok(responseData);
        } catch (SdkException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Error uploading image to S3: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Error constructing image URL: " + e.getMessage()));
        }
    }
*/
    private Map<String, String> createErrorResponse(String errorMessage) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        return errorResponse;
    }

}
