package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.study.service.UserService.UserCommandService;
import umc.study.web.dto.UserDTO.UserRequestDTO;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserViewController {
    private final UserCommandService userCommandService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userJoinDto", new UserRequestDTO.JoinUserDTO());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    private static final Logger logger = LoggerFactory.getLogger(UserViewController.class);

    @PostMapping("/users/signup")
    public String joinUser(@ModelAttribute("userJoinDto") UserRequestDTO.JoinUserDTO request,
                           BindingResult bindingResult,
                           Model model) {
        logger.info("joinUser method called with data: {}", request);
        // 입력된 데이터를 출력
        System.out.println("Received User Data:");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());
        System.out.println("Gender: " + request.getGender());
        System.out.println("BirthDate: " + request.getBirthdate());
        System.out.println("Address: " + request.getAddress());
        System.out.println("FoodCategoryName: " + request.getFoodCategoryName());
        System.out.println("Role: " + request.getRole());

        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
            return "signup";
        }

        try {
            userCommandService.joinUser(request);
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
}
