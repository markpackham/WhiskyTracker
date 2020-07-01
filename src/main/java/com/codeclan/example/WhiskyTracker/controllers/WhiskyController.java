package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/whiskies")
    //http://localhost:8080/whiskies?year=2018
    //http://localhost:8080/whiskies?age=15
    //http://localhost:8080/whiskies?age=15&&distillery=Glendronach
    //http://localhost:8080/whiskies?distillery=Glendronach
    //http://localhost:8080/whiskies?region=Highland
    public ResponseEntity<List<Whisky>> findWhiskyByYear(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="age", required = false) Integer age,
            @RequestParam(name="region", required = false) String region,
            @RequestParam(name="distillery", required = false) String distillery){
        Distillery foundDistillery = distilleryRepository.findByName(distillery);
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year),HttpStatus.OK);
        }
        else if(foundDistillery != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByAgeAndDistillery(age, foundDistillery), HttpStatus.OK);
        }
        else if(foundDistillery != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistillery(foundDistillery), HttpStatus.OK);
        }
        else if(age !=null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByAge(age),HttpStatus.OK);
        }
        else if(region !=null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryRegion(region),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }

}
