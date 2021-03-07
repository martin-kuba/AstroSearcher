package org.astrosearcher.controllers;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.ResponseData;
import org.astrosearcher.classes.mast.TableFromReqByPos;
import org.astrosearcher.classes.mast.services.caom.cone.CaomFields;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
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

        ResponseData responseData = null;

        // TODO: add logic to choose between searching by position or id/name
        // TODO: change List argument type, now its only for MAST tables...
        // TODO: edit method to work with all results in list, not just first one...

        // search by id/name (resolve its position by name, then search by coordinates)
        if (SearchType.ID_NAME.equals(searchBy)) {
            List<PositionInput> resolved = SearchEngine.resolvePositionByNameOrID(searchInput);
//            System.out.println("Resolved: " + resolved);
//            searchBy = SearchType.POSITION.toString();

            // TODO: rework to use all the resolved positions, not just the first one
            PositionInput input = resolved.get(0);
            responseData = SearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius());
        }

        // search by position...
        if (SearchType.POSITION.toString().equals(searchBy)) {
            PositionInput input = new PositionInput(searchInput);

            if (input.isSuccessful()) {
                responseData = SearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius());
            }
        }

        if ( responseData != null && responseData.isRetrieved() ) {

            // only for MAST
            model.addAttribute("mastFields", responseData.getMastResponse().getFields());
            model.addAttribute("mastData", responseData.getMastResponse().getData());
        }

        return "testing";
    }
}
