package org.astrosearcher.controllers;

import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.simbad.SimbadFlux;
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
            model.addAttribute("mastDataMSG", "Unfortunately, there were no data acquired from MAST.");
        }
        model.addAttribute("containsMAST", responseData.containsMastResponse());
    }

    private void processVizierResponse(ResponseData responseData, Model model) {
        if (responseData.containsVizierResponse()) {
            // TODO: load Vizier response
        } else {
            model.addAttribute("vizierDataMSG", "Unfortunately, there were no data acquired from Vizier.");
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
            model.addAttribute("simbadDataMSG", "Unfortunately, there were no data acquired from Simbad.");
        }
        model.addAttribute("containsSimbad", responseData.containsSimbadResponse());
    }

    private String processResponse(ResponseData responseData, Model model) {

        if (responseData.isEmpty()) {
            model.addAttribute("errorMSG", "Unfortunately, there were no data acquired for given input.");
            model.addAttribute("searchOptions", SearchType.values());
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

        if (errors.hasErrors()) {
            model.addAttribute("errorMSG", errors.getFieldError());
            System.out.println("Error: " + errors.getFieldError());
            model.addAttribute("searchOptions", SearchType.values());
            return "index";
        }

        ResponseData responseData;
        try {
            responseData = SearchEngine.process(input);
        } catch (IllegalArgumentException iae) {
            model.addAttribute("errorMSG", iae.getMessage());
            model.addAttribute("searchOptions", SearchType.values());
            return "index";
        }

        return processResponse(responseData, model);
    }
}
