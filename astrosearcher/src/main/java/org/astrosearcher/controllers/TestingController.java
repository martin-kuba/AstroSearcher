package org.astrosearcher.controllers;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.TableFromReqByPos;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.utilities.SearchEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestingController {

//    @GetMapping("testing")
//    public String test(@RequestParam String searchInput, Model model) {
//        System.out.println("test METHOD has been called!!! input: " + searchInput);
//        model.addAttribute("input", searchInput);
//        model.addAttribute("inputis", "haha");
//        return "testing";
//    }

//    @RequestMapping(value = "testing", method = {RequestMethod.GET, RequestMethod.POST})
    @PostMapping("testing")
    public String test(@RequestParam String searchBy, @RequestParam String searchInput, Model model) {
        model.addAttribute("searchOptions", SearchType.values());
//        System.out.println("test METHOD has been called!!! input: " + searchInput);
//        model.addAttribute("input", new MastMashupRequest(searchInput).create());
//        model.addAttribute("input", SearchEngine.findAll(searchInput));
//        model.addAttribute("inputis", "haha");

        List<TableFromReqByPos> res = new ArrayList<>();

        // TODO: add logic to choose between searching by position or id/name
        // TODO: change List argument type, now its only for MAST tables...
        // TODO: edit method to work with all results in list, not just first one...

        if (SearchType.POSITION.toString().equals(searchBy)) {
            PositionInput input = new PositionInput(searchInput);

            if (input.isSuccessful()) {
                System.out.println("Parsing of input was sucessful!");
                res.addAll(SearchEngine.findAllByPosition(input.getRa(), input.getDec(), input.getRadius()));
            } else {
                System.out.println("Parsing of input was not sucessful!");
            }
        }

        if ( !res.isEmpty() ) {
            model.addAttribute("fields", res.get(0).getFields());
            model.addAttribute("rows", res.get(0).getRows());
        }

        return "testing";
    }
}
