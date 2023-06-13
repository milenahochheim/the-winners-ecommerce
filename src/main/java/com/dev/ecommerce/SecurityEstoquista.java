package com.dev.ecommerce;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Order(3)
public class SecurityEstoquista extends WebSecurityConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                // neste método que vamos tratar os usuários

                auth.jdbcAuthentication().dataSource(dataSource)
                                .usersByUsernameQuery(
                                                "select email as username, senha as password, 1 as enable from funcionario where email=?")
                                .authoritiesByUsernameQuery(
                                                "select funcionario.email as username, cargo.nome as authority from permissoes inner join funcionario on funcionario.id=permissoes.funcionario_id inner join cargo on permissoes.cargo_id=cargo.id where funcionario.email=?")
                                .passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                                .antMatchers("/estoquista/login").permitAll()
                                .antMatchers("/estoquista/**").hasAuthority("estoquista")
                                .antMatchers("/estoquista/**").authenticated()
                                .and()
                                .formLogin()
                                .loginPage("/estoquista/login").failureUrl("/estoquista/login")
                                .loginProcessingUrl("/estoquista")
                                .defaultSuccessUrl("/estoquista").usernameParameter("username")
                                .passwordParameter("password").and()
                                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/estoquista/logout"))
                                .logoutSuccessUrl(
                                                "/estoquista/login")
                                .deleteCookies("JSESSIONID").and().exceptionHandling()
                                .accessDeniedPage("/negadoEstoquista").and().csrf().disable();

        }

}
