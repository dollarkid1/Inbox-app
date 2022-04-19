package io.javabrains;

import io.javabrains.inbox.Folder;
import io.javabrains.inbox.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@SpringBootApplication
@RestController
@ConfigurationPropertiesScan
public class InboxApp {

//	@Autowired
//	FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);
	}

	/**
	* This is necessary to have the spring boot app use the Astra secure bundle
	 * to connect to the database
	* */
	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties){
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

//	@PostConstruct
//	public void init(){
//
//		folderRepository.save(new Folder("dkzy", "Inbox","blue"));
//		folderRepository.save(new Folder("dkzy", "Sent","green"));
//		folderRepository.save(new Folder("dkzy", "Important","yellow"));
//
//	}

}