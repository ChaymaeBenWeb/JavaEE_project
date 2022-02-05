package application.office.service;

import java.util.List;

import org.springframework.data.domain.Page;

import application.office.model.Lieu;

public interface ILieuService {

	Lieu saveLieu(Lieu l);// objet de type Lieu et l'en register dans bd
	Lieu updateLieu(Lieu l);// ...//   // le modifier  //
	void deleteLieu(Lieu l);  // c'est une entité
	void deleteLieuById(String id);  // juste l'id
	Lieu getLieu(String id);  // retourn objet Lieu via Id
	List<Lieu> getAllLieux();   // la liste

	List<Lieu> findByCodeInsee(String CodeInsee);
	Page<Lieu> findByNomComContains(String nomCom, int page, int size);
	Page<Lieu> getAllLieuxParPage(int page, int size);

}


