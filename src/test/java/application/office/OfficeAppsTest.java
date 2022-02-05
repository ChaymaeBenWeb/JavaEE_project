package application.office;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import application.office.model.Celebrite;
import application.office.model.Monument;
import application.office.repository.CelebriteRepository;
import application.office.repository.MonumentRepository;
import application.office.service.ICelebriteService;
import application.office.service.IMonumentService;


@SpringBootTest
class OfficeAppsTest {


	@Autowired   // pour que spring injecte cet objet de type repository
	private CelebriteRepository celebriteRepository; // declaration objet de type de notre interface
	private ICelebriteService celebriteService;

	@Autowired
	private MonumentRepository monumentRepository;
	private IMonumentService iMonumentService;
	@Test
	public void testCreationCelebrite() {
		Celebrite cel1 = new  Celebrite(25, "Fabre", "François-Xavier", "Française", "18ème");

		celebriteRepository.save(cel1);  // Jpa repository fourni toute les methodes necessaire
	}

	@Test
	public void testFindCelebrite() {
		Celebrite c = celebriteRepository.findById(25).get();
		System.out.println(c);

	}

	@Test
	public void testUpdateCelebrite() {
		Celebrite c = celebriteRepository.findById(25).get();
		c.setEpoque("19ème");
		celebriteRepository.save(c);
		System.out.println(c);
	}

	@Test
	public void testDeleteCelebrite() {
		celebriteRepository.deleteById(25);

	}



	@Test
	public void testFindAllCelebrite() {
		List<Celebrite> celebs = celebriteRepository.findAll();

		for (Celebrite c:celebs)
			System.out.println(c);
	}

	
	
	@Test
	public void testFindMonument() {
		Monument m = monumentRepository.findById("spfb05ty554b").get();
		System.out.println(m);
	}

	@Test
	public void testFindAllMonuments() {
		List<Monument> mnmts = monumentRepository.findAll();

		for (Monument m:mnmts)
			System.out.println(m);
	}

}




