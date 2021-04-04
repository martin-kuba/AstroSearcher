package org.astrosearcher.controllers;

import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.classes.constants.messages.InformationMSG;
import org.astrosearcher.classes.simbad.SimbadFlux;
import org.astrosearcher.enums.VizierCatalogueSearch;
import org.astrosearcher.enums.simbad.SimbadServices;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.SearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class TestingController {

    private void processMastResponse(ResponseData responseData, Model model) {
        if (responseData.containsMastResponse()) {
            model.addAttribute("mastFields", responseData.getMastResponse().getFields());
            model.addAttribute("mastData", responseData.getMastResponse().getData());
        } else {
            model.addAttribute("mastDataMSG", InformationMSG.NO_MAST_DATA);
        }
        model.addAttribute("containsMAST", responseData.containsMastResponse());
    }

    private void processVizierResponse(ResponseData responseData, Model model) {
        if (responseData.containsVizierResponse()) {
            // TODO: load Vizier response

            model.addAttribute("vizierType", responseData.getVizierResponse().getType());
            model.addAttribute("vizierFields", responseData.getVizierResponse().getFields());
            model.addAttribute("vizierData", responseData.getVizierResponse().getData());
        } else {
            model.addAttribute("vizierDataMSG", InformationMSG.NO_VIZIER_DATA);
        }
        model.addAttribute("containsVizier", responseData.containsVizierResponse());
    }

    private void processSimbadResponse(ResponseData responseData, Model model) {
        if (responseData.containsSimbadResponse()) {
            // TODO: load Simbad response
            model.addAttribute("simbadFields", responseData.getSimbadResponse().getFields());
            model.addAttribute("simbadData", responseData.getSimbadResponse().getData());
            model.addAttribute("simbadType", responseData.getSimbadResponse().getType().name());
            model.addAttribute("simbadResponse", responseData.getSimbadResponse());

            if (responseData.getSimbadResponse().getType() == SimbadServices.SIMBAD_ID) {
                model.addAttribute("fluxFields", SimbadFlux.class.getFields());
            }
        } else {
            model.addAttribute("simbadDataMSG", InformationMSG.NO_SIMBAD_DATA);
        }
        model.addAttribute("containsSimbad", responseData.containsSimbadResponse());
    }

    private String processResponse(ResponseData responseData, Model model) {

        if (responseData.isEmpty()) {
            model.addAttribute("errorMSG", InformationMSG.NO_DATA_AT_ALL);
            model.addAttribute("searchOptions", SearchType.values());
            model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
            return "index";
        }

        processMastResponse(responseData, model);
        processVizierResponse(responseData, model);
        processSimbadResponse(responseData, model);
        return "testing";
    }

    @PostMapping("testing")
    public String test(@ModelAttribute @Valid SearchFormInput input,
                       Errors errors, Model model) {

        if (errors.hasFieldErrors()) {
//            System.out.println("test\n");
            model.addAttribute("errorMSG", errors.getFieldError().getDefaultMessage());
//            System.out.println("Error: " + errors.getFieldError());
            model.addAttribute("searchOptions", SearchType.values());
            model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
            return "index";
        } else if (errors.hasGlobalErrors()) {
            model.addAttribute("errorMSG", errors.getGlobalError().getDefaultMessage());
//            System.out.println("Error: " + errors.getFieldError());
            model.addAttribute("searchOptions", SearchType.values());
            model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
            return "index";
        }



        ResponseData responseData;
        try {
            responseData = SearchEngine.process(input);
        } catch (IllegalArgumentException iae) {
            model.addAttribute("errorMSG", iae.getMessage());
            model.addAttribute("searchOptions", SearchType.values());
            model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
            return "index";
        }

        return processResponse(responseData, model);
    }

    @GetMapping("testing")
    public String getSearch(@RequestParam String id, Model model) {

        ResponseData responseData;
        try {
            responseData = SearchEngine.process(new SearchFormInput(
                    SearchType.ID_NAME.toString(),
                    id,
                    Limits.DEFAULT_PAGE, Limits.DEFAULT_PAGESIZE,
                    VizierCatalogueSearch.code.toString(),
                    VizierConstants.DEFAULT_CATALOG,
                    null));
        } catch (IllegalArgumentException iae) {
            model.addAttribute("errorMSG", iae.getMessage());
            model.addAttribute("searchOptions", SearchType.values());
            model.addAttribute("vizierOptions", VizierCatalogueSearch.values());
            return "index";
        }

        return processResponse(responseData, model);
    }
}
