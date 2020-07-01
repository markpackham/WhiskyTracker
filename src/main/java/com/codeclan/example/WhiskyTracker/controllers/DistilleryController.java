package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
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
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/distilleries")
    //http://localhost:8080/distilleries?region=Highland
    //http://localhost:8080/distilleries?age=12
    public ResponseEntity<List<Distillery>> getDistilleriesByRegion(
            @RequestParam(name = "region", required = false
            ) String region,
            @RequestParam(name="age", required = false) Integer age
            ){
        if(region != null){
            return new ResponseEntity<>(distilleryRepository.findDistilleryByRegion(region), HttpStatus.OK);
        }
        else if(age != null){
            return new ResponseEntity<>(distilleryRepository.findDistilleryByWhiskiesAge(age), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
        }
    }

}
