package com.app.jujamanru.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    /* 사용안함
    @GetMapping("/name")
    private ResponseEntity<Resource> getImage(@PathVariable("name") String name) {
        var image = imageService.getImage(name);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 이미지 타입에 맞게 변경
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(image);
    }
     */
}
