package org.astrosearcher.controllers;

import com.google.gson.Gson;
import org.astrosearcher.classes.constants.messages.InformationMSG;
import org.astrosearcher.classes.sesame.SesameResponse;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;
import org.astrosearcher.utilities.SesameSearchEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

        return "identifiers";
    }

    @GetMapping("identifiers/json")
    public ResponseEntity<Object> loadJson(@RequestParam String id) {
        SesameResponse response = SesameSearchEngine.findAllAliasesForId(id);

        Gson gson = new Gson();

        if ( response.getAliases().isEmpty() ) {
            return new ResponseEntity<>(gson.toJson(InformationMSG.NO_SESAME_DATA), HttpStatus.NO_CONTENT);
        }

        return ConnectionUtils.prepareJsonResponseEntity(response);
    }
}
