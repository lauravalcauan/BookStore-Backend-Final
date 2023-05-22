package com.bookstore.serviceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphController {

    @GetMapping("/displayPieChart")
    public String pieChart(Model model){
        model.addAttribute("ScatteredMinds",10);
        model.addAttribute("Whenthebodysaysno",37);
        model.addAttribute("AtomicHabbits",20);
        model.addAttribute("Candide",17);
        model.addAttribute("Sapiens",8);
        model.addAttribute("others",8);

        return "pieChart";
    }

}

