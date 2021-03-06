package com.example.bankapplication.controller;

import com.example.bankapplication.model.User.Address;
import com.example.bankapplication.model.User.User;
import com.example.bankapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByUserName(user.getLogin());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "Uzytkownik o takim loginie istnieje");
        }
        if (bindingResult.hasErrors()) {

        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "Użytkownik zarejestrowany pomyślnie!");
            model.addAttribute("user", new User());
        }
        return "registration";
    }

    @RequestMapping(value="/success")
    public String home(Model model){
        return "home";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, false);
        binder.registerCustomEditor(Date.class, "birthdayDate", dateEditor);

        /*DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        binder.registerCustomEditor(Float.class, "price", new CustomNumberEditor(Float.class, numberFormat, false));
*/
        binder.setDisallowedFields("creationdDate");
    }

    @RequestMapping(value="/profile", method= RequestMethod.GET)
    public String showForm(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        if(user.getAddress() == null){
            user.setAddress(new Address());
        }

        model.addAttribute("user", user);

        return "profile";
    }



    @RequestMapping(value="/profile", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("user") User u){
        userService.editUser(u);

        return "redirect:profile";
    }

    @GetMapping(value = "/user.html", params = "did")
    public String deleteUser(Long did){
        userService.deleteUser(did);
        return "redirect:/logout";
    }
    @GetMapping(value = "/user.html", params = "didi")
    public String deleteAdminUser(Long didi){
        userService.deleteUser(didi);
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
    @GetMapping(value = "/user", params = "id")
    public String getUser(Model model, Long id){
        model.addAttribute("users", userService.getUser(id));
        return "userDetails";
    }
    @GetMapping(value = "/user/dezactive", params = "id")
    public String dezactiveUser(Model model, Long id){
        userService.dezactiveUser(id);
        return "redirect:/users";
    }
    @GetMapping(value = "/user/active", params = "id")
    public String activeUser(Model model, Long id){
        userService.activeUser(id);
        return "redirect:/users";
    }



}