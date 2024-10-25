package com.bassem.campaignmaster.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableEncryptableProperties
public class ApplicationConfig {

    @Bean
    public MessageDigest messageDigest(){
        try {
            return MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex){
            return null;
        }
    }
}
