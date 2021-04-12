package org.astrosearcher.controllers;

import org.astrosearcher.classes.constants.messages.InformationMSG;
import org.astrosearcher.classes.sesame.SesameResponse;
import org.astrosearcher.utilities.SesameSearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IdentifiersController {

    @GetMapping("identifiers")
    public String load(@RequestParam String id, Model model) {
        SesameResponse response = SesameSearchEngine.findAllAliasesForId(id);

        if ( response.getAliases().isEmpty() ) {
            model.addAttribute("sesameDataMSG", InformationMSG.NO_SESAME_DATA);
        } else {
            model.addAttribute("sesameResponse", SesameSearchEngine.findAllAliasesForId(id));
        }
        model.addAttribute("containsSesameResponse", !response.getAliases().isEmpty());
//        System.out.println("containg sesame reponse: " + response.getAliases().isEmpty());

        return "identifiers";
    }
}
