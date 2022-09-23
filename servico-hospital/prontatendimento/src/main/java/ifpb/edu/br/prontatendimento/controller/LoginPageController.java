package ifpb.edu.br.prontatendimento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginPageController {

    @GetMapping("/login")
    public String login(){
        return "Account/login";
    }
    
}
