//package org.astrosearcher.controllers;
//
//import org.astrosearcher.classes.ResponseData;
//import org.astrosearcher.classes.constants.messages.InformationMSG;
//import org.astrosearcher.classes.sesame.SesameResponse;
//import org.astrosearcher.enums.SearchType;
//import org.astrosearcher.models.SearchFormInput;
//import org.astrosearcher.utilities.SearchEngine;
//import org.astrosearcher.utilities.SesameSearchEngine;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class MeasurementsController {
//
//    @GetMapping("measurements")
//    public String load(@RequestParam String id, Model model) {
//        SearchFormInput input = new SearchFormInput();
//
//        input.setSearchBy(SearchType.ID_NAME.toString());
//        input.setSearchInput(id);
//        input.setQueryVizier(false);
//        input.setQueryMast(false);
//
//        ResponseData response = SearchEngine.process(input);
//
//        if ( response.getAliases().isEmpty() ) {
//            model.addAttribute("sesameDataMSG", InformationMSG.NO_SESAME_DATA);
//        } else {
//            model.addAttribute("sesameResponse", SesameSearchEngine.findAllAliasesForId(id));
//        }
//        model.addAttribute("containsSesameResponse", !response.getAliases().isEmpty());
//
//        return "identifiers";
//    }
//}
