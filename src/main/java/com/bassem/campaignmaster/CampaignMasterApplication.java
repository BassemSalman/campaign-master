package com.bassem.campaignmaster;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CampaignMasterApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CampaignMasterApplication.class);
	}
}
