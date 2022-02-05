package application.office.service;

import java.util.List;

import org.springframework.data.domain.Page;

import application.office.model.Monument;

public interface IMonumentService {

	Monument saveMonument(Monument m);// objet de type monument et l'en register dans bd
	Monument updateMonument(Monument m);// ...//   // le modifier  //
	void deleteMonument(Monument m);  // c'est une entité
	void deleteMonumentById(String id);  // juste l'id
	Monument getMonument(String id);  // retourn objet monument via Id
	List<Monument> getAllMonuments();   // la liste

	List<Monument> findByNomM(String nomM);
	Page<Monument> findByNomMContains(String nomM, int page, int size);

	Page<Monument> getAllMonumentsParPage(int page, int size);

	public double distance(String codeMA, String codeMB);


}
