package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.launchcode.models.JobData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {


    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {

       //list of jobs
        ArrayList<HashMap<String, String>> jobs;

        //if search = all, find by value passing search term
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        }

        //TODO fix this
        //if search field is blank, show all jobs
        else if (searchType.equals("")) {
           return "search";
        }

        //search by type and term
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        //per instructions, pass columns and list controller as in other search method
        model.addAttribute("columns", ListController.columnChoices);

        //pass jobs arrayList of hashmpas
        model.addAttribute("jobs", jobs);


        return "search";

    }
}
