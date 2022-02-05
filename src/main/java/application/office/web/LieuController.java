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

import application.office.model.Lieu;
import application.office.service.ILieuService;


@Controller
public class LieuController {


    @Autowired
    ILieuService iLieuService;


    @RequestMapping("/showCreateL")  //@..lorsque l'url demander est celui la donc j'affiche la jsp
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreate(ModelMap modelMap) throws ParseException {
        modelMap.addAttribute("newLieu", new Lieu());
        modelMap.addAttribute("mode", "new");
        return "Lieux/formLieu";// le nom de la vue creer
    }

    @RequestMapping("/saveLieu")  // action save Lieu
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveLieu(@ModelAttribute("newLieu") Lieu lieu, // lors de la creation de la vue du il recupere l'objet

                           ModelMap modelMap) throws ParseException {


        Lieu saveLieu = iLieuService.saveLieu(lieu);  //la methode save du service avec comme paramettre l'objet celeb
        String msg = "Lieu enregistré avec codeM " + saveLieu.getCodeInsee();
        modelMap.addAttribute("msg", msg); //je passe le message à modelMap declarer en haut
        modelMap.addAttribute("mode", "new");
        return "Lieux/formLieu";

    }

    @RequestMapping("/ListeLieux")
    public String listeLieus(ModelMap modelMap,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "4") int size) {
        //List<Lieu>lieux = LieuService.getAllLieus();
        //modelMap.addAttribute("Lieus", lieux);
        //return "ListeLieus";

        Page<Lieu> lieux = iLieuService.getAllLieuxParPage(page, size);
        modelMap.addAttribute("lieux", lieux);
        modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Lieux/listeLieux";
    }

	@RequestMapping("/SearchLieux")
	public String searchMonuments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "4") int size) {
		Page<Lieu> lieux = iLieuService.findByNomComContains(keyword, page, size);
		modelMap.addAttribute("lieux", lieux);
		modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Lieux/listeLieux";
	}

    @RequestMapping("/supprimerLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String supprimerLieu(@RequestParam("id") String id,
                                ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "4") int size) {
        //Lieu c = new Lieu();
        //c.setCodInsee(id);
        iLieuService.deleteLieuById(id);

        Page<Lieu> lieux = iLieuService.getAllLieuxParPage(page, size);
        modelMap.addAttribute("lieux", lieux);
        modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "Lieux/listeLieux";
    }

    @RequestMapping("/modifierLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editerLieu(@RequestParam("id") String id,
                             ModelMap modelMap) {
        Lieu l = iLieuService.getLieu(id);
        modelMap.addAttribute("lieu", l);
        modelMap.addAttribute("mode", "edit");
        return "Lieux/formLieu";
    }

    @RequestMapping("/updateLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateLieu(@ModelAttribute("lieu") Lieu lieu,
                             ModelMap modelMap) {
        iLieuService.updateLieu(lieu);
        List<Lieu> lieux = iLieuService.getAllLieux();
        modelMap.addAttribute("lieux", lieux);
        return "Lieux/ListeLieux";


    }


}
