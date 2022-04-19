package io.javabrains.controllers;

import io.javabrains.inbox.Folder;
import io.javabrains.inbox.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class InboxController {


    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal OAuth2User principal){
        if (principal == null || !StringUtils.hasText(principal.getAttribute("name"))){
            return "index";
        }
        else return "inbox-page";
    }




}
