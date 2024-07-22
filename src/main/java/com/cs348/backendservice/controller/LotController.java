package com.cs348.backendservice.controller;

import com.cs348.backendservice.model.LotResponse;
import com.cs348.backendservice.repository.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotController {

    @Autowired
    private Lot lotRepository;

    @GetMapping("/lots")
    public ResponseEntity<List<LotResponse>> getAllLots() {
        List<LotResponse> lots = lotRepository.getLotInfo();
        return ResponseEntity.ok(lots);
    }
}


