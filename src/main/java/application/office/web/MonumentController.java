package application.office.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.office.model.Celebrite;
import application.office.model.Monument;
import application.office.service.ICelebriteService;
import application.office.service.IMonumentService;


@Controller// c'est un stereotype
public class MonumentController {
    @Autowired
    IMonumentService iMonumentService;

    @Autowired  // injecter l'instanciation de ICelebriteService et avoir l'accès à toutes les methodes
            ICelebriteService celebriteService;

    @RequestMapping("/showCreateM")  //@..lorsque l'url demander est celui la donc j'affiche la jsp
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreateM(ModelMap modelMap) throws ParseException {
        List<Celebrite> celebs = celebriteService.getAllCelebrites();
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("celebrite", celebs);
        return "Monuments/formMonument";
    }

    @RequestMapping("/saveMonument")  // action save monument
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveMonument(@ModelAttribute("monument") Monument monument, // lors de la creation de la vue du il recupere l'objet
                               ModelMap modelMap) throws ParseException {


        Monument saveMonument = iMonumentService.saveMonument(monument);  //la methode save du service avec comme paramettre l'objet celeb
        String msg = "monument enregistré avec codeM " + saveMonument.getCodeM();

        List<Celebrite> celebs = celebriteService.getAllCelebrites();
        modelMap.addAttribute("msg", msg); //je passe le message à modelMap declarer en haut
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("celebrite", celebs);
        return "Monuments/formMonument";

    }

    @RequestMapping("/ListeMonuments")
    public String listeMonuments(ModelMap modelMap,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "4") int size) {


        Page<Monument> mnmts = iMonumentService.getAllMonumentsParPage(page, size);
        modelMap.addAttribute("monuments", mnmts);
        modelMap.addAttribute("pages", new int[mnmts.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Monuments/listeMonuments";
    }

	@RequestMapping("/SearchMonuments")
	public String searchMonuments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "4") int size) {
		Page<Monument> mnmts = iMonumentService.findByNomMContains(keyword, page, size);
		modelMap.addAttribute("monuments", mnmts);
		modelMap.addAttribute("pages", new int[mnmts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Monuments/listeMonuments";
	}

    @RequestMapping("/supprimerMonument")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String supprimerMonument(@RequestParam("id") String id,
                                    ModelMap modelMap,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "4") int size) {
        //Monument c = new Monument();
        //c.setNumMonument(id);
        iMonumentService.deleteMonumentById(id);

        Page<Monument> mnmts = iMonumentService.getAllMonumentsParPage(page, size);
        modelMap.addAttribute("monuments", mnmts);
        modelMap.addAttribute("pages", new int[mnmts.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "Monuments/listeMonuments";
    }

    @RequestMapping("/modifierMonument")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editerMonument(@RequestParam("id") String id,
                                 ModelMap modelMap) {
        Monument m = iMonumentService.getMonument(id);
        List<Celebrite> celebs = celebriteService.getAllCelebrites();
        modelMap.addAttribute("celebrite", celebs);

        modelMap.addAttribute("monument", m);
        modelMap.addAttribute("mode", "edit");
        return "Monuments/formMonument";
    }

    @RequestMapping("/updateMonument")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateMonument(@ModelAttribute("monument") Monument monument,
                                 ModelMap modelMap) {
        iMonumentService.updateMonument(monument);
        List<Monument> mnmts = iMonumentService.getAllMonuments();
        List<Celebrite> celebs = celebriteService.getAllCelebrites();

        modelMap.addAttribute("monuments", mnmts);
        modelMap.addAttribute("celebrite", celebs);
        return "Monuments/ListeMonuments";
    }

    @RequestMapping(value = "/formMonumentDistance")
    public String formMonumentDistance(
            ModelMap modelMap) {
        List<Monument> mnmts = iMonumentService.getAllMonuments();
        modelMap.addAttribute("monuments", mnmts);
        return "distance";
    }

    @RequestMapping(value = "/distance", method = RequestMethod.GET)
    public String distance(Model model, String codeMA, String codeMB,
                           ModelMap modelMap) {
        Monument m1 = iMonumentService.getMonument(codeMA);
        Monument m2 = iMonumentService.getMonument(codeMB);
        double distance = iMonumentService.distance(codeMA, codeMB);
        model.addAttribute("distance", distance);

        List<Monument> mnmts = iMonumentService.getAllMonuments();
        modelMap.addAttribute("monuments", mnmts);
        return "distance";
    }


}
