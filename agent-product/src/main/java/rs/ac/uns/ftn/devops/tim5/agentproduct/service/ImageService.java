package rs.ac.uns.ftn.devops.tim5.agentproduct.service;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.devops.tim5.agentproduct.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.devops.tim5.agentproduct.model.Image;

import java.io.IOException;

public interface ImageService {

    Image upload(MultipartFile file) throws IOException;

    Image getImage(Long id) throws ResourceNotFoundException;
}
