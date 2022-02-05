package application.office.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import application.office.model.Departement;
import application.office.service.IDepartementService;

@Controller
public class DepartemntController {

	@Autowired // injecter l'instanciation de IDepartementService et avoir l'accès à toutes les
				// methodes
	IDepartementService iDepartementService;

	@RequestMapping("/showCreateD") // @..lorsque l'url demander est celui la donc j'affiche la jsp
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String showCreate(ModelMap modelMap) throws ParseException {
		modelMap.addAttribute("mode", "new");
		return "Departements/formDepartement";// le nom de la vue creer
	}

	@RequestMapping("/saveDepartement") // action save Departement
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String saveDepartement(@ModelAttribute("departement") Departement departement, // lors de la creation de la
																							// vue du il recupere
																							// l'objet

			ModelMap modelMap) throws ParseException {

		Departement saveDepartement = iDepartementService.saveDepartement(departement); // la methode save du service
																						// avec comme paramettre l'objet
																						// celeb
		String msg = "clebrite enregistré avec Id " + saveDepartement.getDep();
		modelMap.addAttribute("msg", msg); // je passe le message à modelMap declarer en haut
		modelMap.addAttribute("mode", "new");
		return "Departements/formDepartement";

	}

	@RequestMapping("/ListeDepartements")
	public String listeDepartements(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "6") int size) {
		// List<Departement>depts = DepartementService.getAllDepartements();
		// modelMap.addAttribute("Departements", depts);
		// return "ListeDepartements";

		Page<Departement> depts = iDepartementService.getAllDepartementsParPage(page, size);
		modelMap.addAttribute("departements", depts);
		modelMap.addAttribute("pages", new int[depts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "Departements/listeDepartements";
	}

	@RequestMapping("/SearchDepartments")
	public String searchDepartments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "4") int size) {
		Page<Departement> mnmts = iDepartementService.findByNomDepContains(keyword, page, size);
		modelMap.addAttribute("departements", mnmts);
		modelMap.addAttribute("pages", new int[mnmts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Departements/listeDepartements";
	}

	@RequestMapping("/supprimerDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String supprimerDepartement(@RequestParam("id") String id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "6") int size) {
		// Departement c = new Departement();
		// c.setNumDepartement(id);

		iDepartementService.deleteDepartementById(id);

		Page<Departement> depts = iDepartementService.getAllDepartementsParPage(page, size);
		modelMap.addAttribute("departements", depts);
		modelMap.addAttribute("pages", new int[depts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "Departements/listeDepartements";
	}

	

	@RequestMapping("/modifierDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String editerDepartement(@RequestParam("id") String id, ModelMap modelMap) // affichage coller les shamps de
																						// modification
	{

		Departement d = iDepartementService.getDepartement(id);
		modelMap.addAttribute("departement", d);
		modelMap.addAttribute("mode", "edit");
		return "Departements/formDepartement";
	}

	@RequestMapping("/showSearch") // @..lorsque l'url demander est celui la don c j'affiche la jsp
	public String showFindDepartment(ModelMap modelMap) throws ParseException {
		System.out.println("Show Search");
//		modelMap.addAttribute("departement", null);
		return "Departements/findDepartement";// le nom de la vue creer
	}

	@RequestMapping("/updateDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateDepartement(@ModelAttribute("departement") Departement departement, ModelMap modelMap) { // modification
																													// dfes
																													// sauvgardes
		iDepartementService.updateDepartement(departement);
		List<Departement> depts = iDepartementService.getAllDepartements();
		modelMap.addAttribute("departements", depts);
		return "Departements/ListeDepartements";

	}

}
