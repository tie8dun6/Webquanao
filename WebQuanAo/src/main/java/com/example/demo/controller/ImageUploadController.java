package com.example.demo.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller

public class ImageUploadController {
    @RequestMapping(value = "getimage/{image}", method = RequestMethod.GET)
    @ResponseBody
    @GetMapping
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image) {
        if (!image.equals("") || image != null) {
            try {
                Path filename = Paths.get("D:\\shop_quan_ao3\\WebQuanAo\\src\\main\\webapp\\img", image);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (IOException e) {
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
