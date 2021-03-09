package org.astrosearcher.controllers;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.utilities.SearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestingController {

    @PostMapping("testing")
    public String test(@RequestParam String searchBy, @RequestParam String searchInput, Model model) {

        ResponseData responseData = null;

        // TODO: add logic to choose between searching by position or id/name
        // TODO: change List argument type, now its only for MAST tables...
        // TODO: edit method to work with all results in list, not just first one...

        // search by id/name (resolve its position by name, then search by coordinates)
        if (SearchType.ID_NAME.equals(searchBy)) {
            List<PositionInput> resolved = SearchEngine.resolvePositionByNameOrID(searchInput);
//            System.out.println("Resolved: " + resolved);
//            searchBy = SearchType.POSITION.toString();

            if (resolved.isEmpty()) {
                responseData = new ResponseData();
            } else {

                // TODO: rework to use all the resolved positions, not just the first one
                PositionInput input = resolved.get(0);

                responseData = SearchEngine.findAllByPosition(input);
            }
        }

        // search by position...
        if (SearchType.POSITION.toString().equals(searchBy)) {
            PositionInput input = new PositionInput(searchInput);

            if (input.isSuccessful()) {
                responseData = SearchEngine.findAllByPosition(input);
            } else {
                model.addAttribute("errorMSG", input.getMessage());
                model.addAttribute("searchOptions", SearchType.values());
                return "index";
            }
        }

        if ( responseData != null && !responseData.isEmpty() ) {

            if (responseData.containsMastResponse()) {
                // only for MAST
                model.addAttribute("mastFields", responseData.getMastResponse().getFields());
                model.addAttribute("mastData", responseData.getMastResponse().getData());
            } else {
                model.addAttribute("mastDataMSG", "Unfortunately, there were no data acquired from MAST.");
            }
            model.addAttribute("containsMAST", responseData.containsMastResponse());

            if (responseData.containsVizierResponse()) {
                // TODO: load Vizier response
            } else {
                model.addAttribute("vizierDataMSG", "Unfortunately, there were no data acquired from Vizier.");
            }
            model.addAttribute("containsVizier", responseData.containsVizierResponse());

            if (responseData.containsSimbadResponse()) {
                // TODO: load Simbad response
            } else {
                model.addAttribute("simbadDataMSG", "Unfortunately, there were no data acquired from Simbad.");
            }
            model.addAttribute("containsSimbad", responseData.containsSimbadResponse());

        } else {
            model.addAttribute("errorMSG", "Unfortunately, there were no data acquired for given input.");
            model.addAttribute("searchOptions", SearchType.values());
            return "index";
        }

        return "testing";
    }
}
