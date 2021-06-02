package rs.ac.uns.ftn.devops.tim5.agentproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;
import rs.ac.uns.ftn.devops.tim5.agentproduct.service.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/{id}")
    public HttpServletResponse getImage(@PathVariable Long id, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        Image image = imageService.getImage(id);
        response.getOutputStream().write(image.getContent());
        return response;
    }

    @PostMapping
    public ResponseEntity<Long> imageUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Image image = imageService.upload(file);
        return new ResponseEntity<>(image.getId(), HttpStatus.OK);
    }
}
