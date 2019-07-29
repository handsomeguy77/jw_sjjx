package com.bm.jw.web;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import java.util.Collection;

@Controller
public class SysUserController {
//    @GetMapping("/login")
//    public String Login(){
//        return "login.html";
//    }
//    @GetMapping("/none")
//    public String Error(){
//        return "error.html";
//    }
//    @GetMapping("/index")
//    public String Index(Model model){
//        Object principal= SecurityContextHolder.getContext().getAuthentication();
//        String username=((Authentication) principal).getName();
//        Collection<? extends GrantedAuthority> roles =((Authentication) principal).getAuthorities();
//        String index="";
//        switch (roles.toString()){
//            case "[ROLE_ADMIN]":
//                index="admin/index.html";
//                break;
//            case "[ROLE_DIRECTOR]":
//                index="director/index.html";
//                break;
//            case "[ROLE_MEMBER]":
//                index="member/index.html";
//                break;
//        }
//        model.addAttribute("username",username);
//        return index;
//    }

    @GetMapping("/admin")
    public String Admin(){
        return "admin/index.html";
    }
    @GetMapping("/director")
    public  String Director(){
        return "director/index.html";
    }
    @GetMapping("/member")
    public String Member(){
        return "member/index.html";
    }
}
