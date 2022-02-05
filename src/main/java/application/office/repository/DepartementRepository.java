package application.office.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import application.office.model.Departement;


public interface DepartementRepository extends JpaRepository<Departement, String> {


	List<Departement> findByDep(String dep );

	List<Departement> findByDepContains(String dep);
	Page<Departement> findByDepContains(String dep, Pageable pageable);
	Page<Departement> findByNomDepContains(String depNom, Pageable pageable);
}
