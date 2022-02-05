package application.office.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import application.office.model.User;
import application.office.web.dto.UserRegistrationDto;



public interface UserService extends UserDetailsService{
	// l'implementation de UserDetailsService  déclarer dans la configuration  pour permettre de recuperer les details de l'utilisateur
	User save(UserRegistrationDto registrationDto);
}
