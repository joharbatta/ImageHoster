package ImageHoster.service;

import ImageHoster.model.Image;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAllImages() {
        return imageRepository.getAllImages();
    }

    public void uploadImage(Image image) {
        imageRepository.uploadImage(image);
    }

    public Image getImageByTitle(String title) {
        return imageRepository.getImageByTitle(title);
    }

}
