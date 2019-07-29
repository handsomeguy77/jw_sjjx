//package com.bm.jw.conf.sec;
//
//import com.bm.jw.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.util.DigestUtils;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Bean
//    UserDetailsService getUserdetailService(){
//        return new UserDetailsServiceImpl();
//    }
//    @Bean
//    BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(){
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));//MD5密文验证
//            }
//        };
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(getUserdetailService()).passwordEncoder(passwordEncoder());
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/public/**").hasAnyRole("ADMIN","DIRECTOR","MEMBER")
//                .antMatchers("/**").permitAll()
//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index").usernameParameter("username")
//                .passwordParameter("password").failureUrl("/none").permitAll()
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
//                .and().csrf().disable();
//    }
//}