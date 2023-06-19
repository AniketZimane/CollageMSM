package com.CollageMSM.CollageMSM.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController
{
    @GetMapping("/default/")
    public String showDefaultPageAfterLogin(HttpServletRequest request)
    {
        if (request.isUserInRole("ROLE_ADMIN"))
        {
            return "redirect:/admin/dashboard/";
        }
        else if (request.isUserInRole("ROLE_USER"))
        {
            return "redirect:/";
        }
        // similarly you can check for other designations like manager, analyst etc

        return "redirect:/";
    }
}


