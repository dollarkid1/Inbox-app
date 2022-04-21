package io.javabrains.controllers;

import io.javabrains.inbox.Folder;
import io.javabrains.inbox.FolderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
public class InboxController {

    @Autowired
    private FolderRepository folderRepository;

    @GetMapping(value = "/")
    public String homePage(@AuthenticationPrincipal OAuth2User principal,
                           Model model){
        log.info("PRINCIPAL -> {}", principal);
        log.info("MODEL -> {}", model);
        if (principal == null || !StringUtils.hasText(principal.getAttribute("login"))){
            return "index";
        }

        String userId = principal.getAttribute("login");
        log.info("USER ID -> {}", userId);
        List<Folder> userFolders = folderRepository.findAllById(userId);
        log.info("USER FOLDER -> {}", userFolders);
        model.addAttribute("userFolders", userFolders);

         return "inbox-page";
    }





}
