package application.office.service;

import java.util.List;

import org.springframework.data.domain.Page;

import application.office.model.Departement;

public interface IDepartementService {

	Departement saveDepartement(Departement d);// objet de type departement et l'en register dans bd
	Departement updateDepartement(Departement d);// ...//   // le modifier //
	void deleteDepartement(Departement d);  // c'est une entité
	void deleteDepartementById(String id);  // juste l'id
	Departement getDepartement(String id);  // retourn objet departement via Id
	List<Departement> getAllDepartements();   // la liste

	List<Departement> findByDep(String dep);
	Page<Departement> findByNomDepContains(String dep, int page, int size);
	Page<Departement> getAllDepartementsParPage(int page, int size);
}
