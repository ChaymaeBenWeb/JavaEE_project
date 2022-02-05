
/* La configuration Suivante permets l'accès aux differents URL : enregistrement , ficher css...
 * donner  acccès à la page de connexion personalisée et la déconnection
 *
 * Aprés ça il faut crerer le password encoders
 * */

package application.office.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import application.office.service.UserService;


@Configuration  // class de cofiguration    Spring   // de calcul
@EnableWebSecurity  // Integration de spring Sécurity avec Spring MVC
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder();
    }

	@Bean //Intégration des donnees spring JP   // Bean est le fournisseur  d'authentification
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);  // on a la meyhode set qui prend comme parametre le srvice  d'ou l'injection de se dernier Mais aussi il doit implementer UserDetailsService
        auth.setPasswordEncoder(passwordEncoder());  
        return auth;
    }

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// class Manager en paramettre
        auth.authenticationProvider(authenticationProvider());
        // passer authenticationProvider en paramettre pour integrer les dependance du Provider
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//HttpSecurity : nous fournissons tout le ressort de configuration
		// Comme l'acces rapide aux ressources statique et configuration URL
		http.authorizeRequests().antMatchers(  // configuration URLs exemple ci-dessous
				 "/registration**",
	                "/js/**",
	                "/css/**",
	                "/img/**").permitAll()
		.anyRequest().authenticated()  /// authentification de toute request ID
		.and()
		.formLogin()
		.loginPage("/login")  // parametre par defaut
		.defaultSuccessUrl("/ListeCelebrites", true)
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// lors du click sur connexion
		.logoutSuccessUrl("/login?logout")  // redirection
		.permitAll();

	}

}
