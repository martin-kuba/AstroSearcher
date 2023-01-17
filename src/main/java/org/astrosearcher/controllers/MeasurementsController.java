package org.astrosearcher.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.astrosearcher.classes.constants.messages.InformationMSG;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;
import org.astrosearcher.utilities.SearchEngine;
import org.astrosearcher.utilities.SesameSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MeasurementsController {

    @Autowired
    private SearchEngine engine;

    @GetMapping("measurements")
    public String load(@RequestParam String id, Model model) {
        SearchFormInput input = new SearchFormInput();

        input.setSearchBy(SearchType.ID_NAME.toString());
        input.setSearchInput(id);
        input.setQueryVizier(false);
        input.setQueryMast(false);

        List<SimbadMeasurementsTable> response = engine.findAllMeasurementsByID(input);

        if ( response.isEmpty() ) {
            model.addAttribute("simbadDataMSG", InformationMSG.NO_SIMBAD_DATA);
        } else {
            model.addAttribute("simbadMeasurements", response);
        }
        model.addAttribute("containsSimbadMeasurements", !response.isEmpty());

        return "measurements";
    }

    @GetMapping("measurements/json")
    public ResponseEntity<Object> loadJson(@RequestParam String id) {
        SearchFormInput input = new SearchFormInput();

        input.setSearchBy(SearchType.ID_NAME.toString());
        input.setSearchInput(id);
        input.setQueryVizier(false);
        input.setQueryMast(false);

        List<SimbadMeasurementsTable> response = engine.findAllMeasurementsByID(input);

        Gson gson = new Gson();

        if ( response.isEmpty() ) {
            return new ResponseEntity<>(gson.toJson(InformationMSG.NO_SIMBAD_DATA), HttpStatus.NO_CONTENT);
        }

        return ConnectionUtils.prepareJsonResponseEntity(response);
    }
}
