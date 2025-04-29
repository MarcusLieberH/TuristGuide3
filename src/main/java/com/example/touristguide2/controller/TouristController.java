package com.example.touristguide2.controller;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.model.TouristTags;
import com.example.touristguide2.model.TouristTowns;
import com.example.touristguide2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    // Endpoint til at vise alle attraktioner (GET /attractions)
    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        model.addAttribute("attractions", touristService.getAllAttractions());
        return "attractionList";
    }

    // Endpoint til at vise detaljerne for en specifik attraktion (GET /attractions/{name})
    @GetMapping("/attractions/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);

        return "attractionDetail";
    }

    // Endpoint til at vise en form for at tilføje en ny attraktion (GET /add)
    @GetMapping("/add")
    public String addAttraction(Model model) {
        model.addAttribute("tags", TouristTags.values());
        model.addAttribute("towns", TouristTowns.values());
        model.addAttribute("attraction", new TouristAttraction());
        return "addAttraction";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction,
                                 @RequestParam("tags") List<String> tags) {
        String imageUrl = attraction.getImage();  // Brug den indtastede URL til billedet

        // Opdater din attraktion med den indtastede billede-URL
        attraction.setImage(imageUrl); // Sæt URL'en som billede i objektet

        // Gem attraktionen med tags
        touristService.addAttraction(attraction, tags);

        return "redirect:/attractions";  // Omdiriger til attractions listen
    }




    // Endpoint til at vise en form for opdatering af en attraktion (GET /attractions/{name}/edit)
    @GetMapping("/attractions/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("tags", TouristTags.values());
        model.addAttribute("towns", TouristTowns.values());
        model.addAttribute("attraction", attraction);
        return "editAttraction";
    }


    // Endpoint til at opdatere attraktionen (POST /update)
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute ("attraction") TouristAttraction attraction) {
        touristService.updateAttraction(attraction);

        return "redirect:/attractions";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/attractions";
    }

}

