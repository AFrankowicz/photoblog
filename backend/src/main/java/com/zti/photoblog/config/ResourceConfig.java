package com.zti.photoblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Resource configuration class
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }

    /**
     * Configuration of security for api endpoints
     *
     * @param  http  an absolute URL giving the base location of the image
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/posts").permitAll()
                .antMatchers("/api/accounts").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/accounts/{username}").permitAll()
                .antMatchers("/api/posts/{id}").permitAll()
                .antMatchers("/api/posts/{id}/comments").permitAll()
                .antMatchers("/api/accounts/{username}/posts").permitAll()
                .antMatchers("/api/publish").hasAuthority("ROLE_USER")
                .antMatchers("/api/posts/{postId}/comment").hasAuthority("ROLE_USER")
                .antMatchers("/api/posts/{postId}/comment/{id}").hasAuthority("ROLE_USER")
                .and().csrf().disable();
    }


}