package application.office.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import application.office.model.Lieu;


public interface LieuRepository extends JpaRepository<Lieu, String> {


	List<Lieu> findByCodeInseeContains(String CodeInsee);
	Page<Lieu> findByCodeInseeContains(String CodeInsee, Pageable pageable);
	Page<Lieu> findByNomComContains(String nomCom, Pageable pageable);

	List<Lieu> findByCodeInsee(String CodeInsee);

}
