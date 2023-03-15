package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("home")
    public String getHomePage(Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        model.addAttribute("credentials", credentialService.getAllCredentials());
        return "home";
    }

    @GetMapping("credential/add-credential")
    public String getResultPage() {
        return "result";
    }

    @PostMapping("credential/add-credential")
    public String addNewCredential(Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        String userName = authentication.getName();
        this.credentialService.addCredential(newCredential, userName);
        model.addAttribute("result", "success");
        return "result";
    }
}
