package ImageHoster.controller;
import ImageHoster.model.User;
import ImageHoster.model.UserProfile;
import ImageHoster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("users/registration")
    public String registration(Model model) {

        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);
        model.addAttribute("User", user);
        return "users/registration";
    }


    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(User user) {

        userService.registerUser(user);
        return "redirect:/users/login";
    }

    @RequestMapping("users/login")  //when get request by default
    public String login() {
        return "users/login";
    }

    @RequestMapping(value = "users/login", method= RequestMethod.POST)
    public String loginUser(User user) {
        boolean userExists = userService.login(user);
        if(userExists) {
            return "redirect:/images";
        }
        else {
            return "users/login";
        }
    }

}
