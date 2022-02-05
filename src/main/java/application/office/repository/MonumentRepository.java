package application.office.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import application.office.model.Monument;


public interface MonumentRepository extends JpaRepository<Monument, String> {


	List<Monument> findByNomM(String nomM);

	List<Monument> findByNomMContains(String nomM);
	Page<Monument> findByNomMContains(String nomM, Pageable pageable);

}
