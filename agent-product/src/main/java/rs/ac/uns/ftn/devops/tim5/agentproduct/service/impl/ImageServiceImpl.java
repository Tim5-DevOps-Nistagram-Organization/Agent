package rs.ac.uns.ftn.devops.tim5.agentproduct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;
import rs.ac.uns.ftn.devops.tim5.agentproduct.repository.ImageRepository;
import rs.ac.uns.ftn.devops.tim5.agentproduct.service.ImageService;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Autowired
    public ImageServiceImpl(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Image upload(MultipartFile file) throws IOException {
        Image image = new Image(null, file.getBytes());
        return repository.save(image);
    }

    @Override
    public Image getImage(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image"));
    }

}
