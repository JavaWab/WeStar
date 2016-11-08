package com.westar.wab;

import com.westar.wab.common.utils.BlowfishEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class UsercenterApplication {
	@Value("${westar.flowfish.key}")
	private String blowfishKey;

	public static void main(String[] args) {
		SpringApplication.run(UsercenterApplication.class, args);
	}
	@Bean(name = "blowfishEncryptor")
	BlowfishEncryptor getBlowfishEncryptor(){
		return new BlowfishEncryptor(blowfishKey);
	}
}
