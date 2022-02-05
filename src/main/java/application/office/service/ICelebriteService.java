package application.office.service;
import java.util.List;

import org.springframework.data.domain.Page;

import application.office.model.Celebrite;

public interface ICelebriteService {

	Celebrite saveCelebrite(Celebrite c);// objet de type celebrite et l'enregister dans bd
	Celebrite updateCelebrite(Celebrite c);// ...//   // le modifier  //
	void deleteCelebrite(Celebrite c);  // c'est une entité
	void deleteCelebriteById(Integer id);  // juste l'id
	Celebrite getCelebrite(Integer id);  // retourn objet celebrité via Id
	List<Celebrite> getAllCelebrites();   // la liste

	List<Celebrite> findByNom(String nom);
	Page<Celebrite> findByNomContains(String nom, int page, int size);
	Page<Celebrite> getAllCelebritesParPage(int page, int size);
}