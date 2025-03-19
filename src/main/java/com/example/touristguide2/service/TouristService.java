package com.example.touristguide2.service;

import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.model.TouristTags;
import com.example.touristguide2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TouristService {

    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    public TouristAttraction addAttraction(TouristAttraction attraction, List<String> tags) {
        // Save the attraction with its tags
        return touristRepository.addAttraction(attraction, tags);
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction) {
        return touristRepository.updateAttraction(attraction);
    }

    public boolean deleteAttraction(String name) {
        return touristRepository.deleteAttraction(name);
    }

    public List<TouristTags> getAttractionTags(String name) {
        return touristRepository.getTagsForAttraction(name);
    }
    public TouristAttraction saveAttraction(TouristAttraction attraction, List<String> tags) {
        return touristRepository.addAttraction(attraction, tags);
    }


}
