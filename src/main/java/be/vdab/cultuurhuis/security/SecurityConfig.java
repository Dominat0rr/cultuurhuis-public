package be.vdab.cultuurhuis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;
import javax.swing.undo.AbstractUndoableEdit;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USERS_BY_USERNAME = "select gebruikersnaam as username, paswoord as password, true as enabled from klanten where gebruikersnaam = ?";
    private static final String AUTHORITIES_BY_USERNAME = "select klanten.gebruikersnaam as username, 'klant' as authorities from klanten where klanten.gebruikersnaam = ?";

    @Bean
    JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
        JdbcDaoImpl impl = new JdbcDaoImpl();
        impl.setDataSource(dataSource);
        impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
        impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
        return impl;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/")
                .and().logout().logoutSuccessUrl("/")
                .and().authorizeRequests()
                .mvcMatchers("/", "/login").permitAll()
                .mvcMatchers("/nieuweklant/**").anonymous()
                .mvcMatchers("/nieuweklant/toevoegen").authenticated()
//                .mvcMatchers("/mandje").authenticated()
                .mvcMatchers("/reservatie/**").authenticated();
        http.httpBasic();
    }
}
