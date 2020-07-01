package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

    List<Whisky> findWhiskyByYear(int year);
    List<Whisky> findWhiskyByAge(int age);
    List<Whisky> findWhiskyByDistillery(Distillery distillery);
    List<Whisky> findWhiskyByAgeAndDistillery(int age, Distillery distillery);
    List<Whisky> findWhiskyByDistilleryRegion(String region);

}
