package com.huawei.it.hicloud.iam.gateway.auth.providers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class CMPPasswordEncoder {
	
    @Bean(name="bCryptpasswordencoder")
    public PasswordEncoder bCryptpasswordencoder(){
        return new BCryptPasswordEncoder();
    }
    
//    @Bean(name="pbkdf2passwordencoder")
//    public PasswordEncoder pbkdf2passwordencoder(){
//        return new Pbkdf2PasswordEncoder();
//    }
    
    public static void main(String[] args){
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	String pwd = encoder.encode("Huawei@CMP123");
    	System.out.println(pwd);
    	boolean ismatched = encoder.matches("Huawei@CMP123", "d894e938f8a8ffafd894e938f8a8ffaf0f1af6ebe64921a6c4dc1cd321acb54f1988c25300a0bfae37f7fb4cf08108df");
    	System.out.println(ismatched);
    }
    
}
