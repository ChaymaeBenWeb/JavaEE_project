package application.office.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import application.office.model.Lieu;
import application.office.repository.LieuRepository;


@Service
public class LieuServiceImpl implements ILieuService {

	@Autowired
    LieuRepository lieuRepository;


	@Override
	public Lieu saveLieu(Lieu l) {
		return lieuRepository.save(l) ;

	}

	@Override
	public Lieu updateLieu(Lieu l) {
		return lieuRepository.save(l) ;

	}

	@Override
	public void deleteLieu(Lieu l) {
		 lieuRepository.delete(l);
	}

	@Override
	public void deleteLieuById(String id) {
		lieuRepository.deleteById(id);
	}

	@Override
	public Lieu getLieu(String id) {
		return lieuRepository.findById(id).get() ;
	}

	@Override
	public List<Lieu> getAllLieux() {
		return lieuRepository.findAll() ;
	}

	@Override
	public List<Lieu> findByCodeInsee(String CodeInsee) {
		return lieuRepository.findByCodeInsee(CodeInsee);
	}

	@Override
	public Page<Lieu>  findByNomComContains(String nomCom, int page, int size) {
		return lieuRepository.findByNomComContains(nomCom, PageRequest.of(page, size));

	}

	@Override
	public Page<Lieu> getAllLieuxParPage(int page, int size) {
		return lieuRepository.findAll(PageRequest.of(page, size));
	}

}
