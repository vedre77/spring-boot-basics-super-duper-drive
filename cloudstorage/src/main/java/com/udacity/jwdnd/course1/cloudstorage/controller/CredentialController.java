package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping("add-credential")
    public String getResultPage() {
        return "result";
    }

    @GetMapping("delete-credential/{credentialId}")
    public String deleteCredentialById(@PathVariable("credentialId") int credentialId, Model model) {
        this.credentialService.deleteCredential(credentialId);
        model.addAttribute("result", "success");
        return "result";
    }

    @PostMapping("add-credential")
    public String addNewCredential(Authentication authentication, @ModelAttribute("newCredential") CredentialForm newCredential, Model model) {
        String userName = authentication.getName();
        this.credentialService.addCredential(newCredential, userName);
        model.addAttribute("result", "success");
        return "result";
    }
}
