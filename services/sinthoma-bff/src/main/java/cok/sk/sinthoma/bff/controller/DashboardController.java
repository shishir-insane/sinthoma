package cok.sk.sinthoma.bff.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    
    @GetMapping("/dashboard")
    @ResponseBody
    public String getDashboard(HttpServletRequest request) {
	Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

}
