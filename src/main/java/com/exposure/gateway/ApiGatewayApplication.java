package com.exposure.gateway;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
//@ComponentScan(basePackages = { "com.exposure.**" })
@EnableJpaRepositories("com.exposure.common")
@EntityScan("com.exposure.common")
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Configuration
	public static class AppConfiguration {
		
	    @LoadBalanced
	    @Bean
	    RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	    
	    @Bean
		public HttpClient getSSLClient() {
			  try {
		             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		                 //信任所有
		                 public boolean isTrusted(X509Certificate[] chain,
		                                 String authType) throws CertificateException {
		                     return true;
		                 }
		             }).build();
		             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		             return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		         } catch (KeyManagementException e) {
		             e.printStackTrace();
		         } catch (NoSuchAlgorithmException e) {
		             e.printStackTrace();
		         } catch (KeyStoreException e) {
		             e.printStackTrace();
		         }
		         return  HttpClients.createDefault();
		  }
	    
	}

}
