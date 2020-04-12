package ImageHoster.controller;

import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;


    @RequestMapping("images")
    public String getUserImages(Model model) {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("images", images);
        return "images";
    }

    @RequestMapping("/images/{title}")
    public String showImage(@PathVariable("title") String title, Model model) {
        Image image = imageService.getImageByTitle(title);
//        System.out.println(image+"bgrtybytrhyt");
        model.addAttribute("image", image);
        return "images/image";
    }
    @RequestMapping("/images/upload")
    public String newImage()
    {
        return "images/upload";
    }
    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public String createImage(@RequestParam("file") MultipartFile file, Image newImage, HttpSession session) throws IOException
    {
        User user = (User) session.getAttribute("loggeduser");
//        System.out.println(user+"fergthryjr6u");
        newImage.setUser(user);
        String uploadedImageData = convertUploadedFileToBase64(file);
        newImage.setImageFile(uploadedImageData);
        newImage.setDate(new Date());
        imageService.uploadImage(newImage);
        return "redirect:/images";
    }

    private String convertUploadedFileToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }


}
