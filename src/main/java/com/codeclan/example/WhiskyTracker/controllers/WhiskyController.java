package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    //http://localhost:8080/whiskies?year=2018
    public ResponseEntity<List<Whisky>> findWhiskyByYear(@RequestParam(name="year", required = false) Integer year){
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskyByYear(year),HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    //Get all the whisky from a particular distillery that's a specific age

    //http://localhost:8080/whiskies?distillery=GlendronachAndAge=2018
    //http://localhost:8080/whiskies?distillery=Glendronach
//    @GetMapping(value = "/whiskies")
//    public ResponseEntity<List<Whisky>> findAllWhiskyByDistilleryAndYear(@RequestParam(name="distillery"));


}
