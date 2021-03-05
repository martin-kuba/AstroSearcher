package org.astrosearcher.controllers;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.TableFromReqByPos;
import org.astrosearcher.classes.mast.caom.cone.CaomFields;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.utilities.SearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestingController {

    @PostMapping("testing")
    public String test(@RequestParam String searchBy, @RequestParam String searchInput, Model model) {
        model.addAttribute("searchOptions", SearchType.values());

        List<TableFromReqByPos> res = new ArrayList<>();

        // TODO: add logic to choose between searching by position or id/name
        // TODO: change List argument type, now its only for MAST tables...
        // TODO: edit method to work with all results in list, not just first one...

        if (SearchType.POSITION.toString().equals(searchBy)) {
            PositionInput input = new PositionInput(searchInput);

            if (input.isSuccessful()) {
                res.addAll(SearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius()));
            }
        }

        if ( !res.isEmpty() ) {

            // only for MAST
            model.addAttribute("columns", CaomFields.values());
            model.addAttribute("mapper", res.get(0).getResponseDataMapper());
            model.addAttribute("rows", res.get(0).getRows());
        }

        return "testing";
    }
}
