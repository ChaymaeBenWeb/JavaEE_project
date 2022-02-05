package application.office.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import application.office.model.Monument;
import application.office.repository.MonumentRepository;
@Service

public class MonumentServiceImpl implements IMonumentService {
	@Autowired
    MonumentRepository monumentRepository;

	@Override
	public Monument saveMonument(Monument m) {

		return monumentRepository.save(m) ;
	}

	@Override
	public Monument updateMonument(Monument m) {

		return monumentRepository.save(m) ;
	}

	@Override
	public void deleteMonument(Monument m) {
		 monumentRepository.delete(m);

	}

	@Override
	public void deleteMonumentById(String id) {
		monumentRepository.deleteById(id);

	}

	@Override
	public Monument getMonument( String id) {
		return monumentRepository.findById(id).get() ;
	}

	@Override
	public List<Monument> getAllMonuments() {
		return monumentRepository.findAll() ;
	}

	@Override
	public List<Monument> findByNomM(String nomM) {
		return monumentRepository.findByNomM(nomM);
	}

	@Override
	public Page<Monument> findByNomMContains(String nomM, int page, int size) {
		return monumentRepository.findByNomMContains(nomM, PageRequest.of(page, size));
	}

	@Override
	public Page<Monument> getAllMonumentsParPage(int page, int size) {

		return monumentRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public double distance(String codeMA, String codeMB) {
		// TODO Auto-generated method stub

			double latitudeMA = monumentRepository.getOne(codeMA).getLatitude();
			double longitudeMA=monumentRepository.getOne(codeMA).getLongitude();
			double latitudeMB=monumentRepository.getOne(codeMB).getLatitude();
			double longitudeMB = monumentRepository.getOne(codeMB).getLongitude();

			double longCarre = longitudeMA -longitudeMB;
				   longCarre =  longCarre*longCarre;

		    double latitCarre = latitudeMA -latitudeMB;
		           latitCarre =  latitCarre* latitCarre;

		    double  D = longCarre + latitCarre;

			// double distance = SloppyMath.haversinKilometers(latitudeMA, longitudeMA, latitudeMB, longitudeMB);
			return Math.sqrt(D);

	}
}
