package com.cs348.backendservice.controller;

import com.cs348.backendservice.repository.LikeLot;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeLotController {

    @Autowired
    private LikeLot lot;

    @PostMapping("/likeLot")
    @Operation(summary = "Like a parking lot", description = "Increase the like number of a parking lot by 1.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liked successfully",
                            content = @Content(mediaType = "String",
                                    examples = @ExampleObject(value = "Successfully increased like count of lot: 1, for booking: 3"))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "String"))
            })
    public ResponseEntity<String> likeLotHandler(@RequestParam int lid, @RequestParam int bid, @RequestParam int uid) {
        try {
            lot.like(lid, bid, uid);
            return new ResponseEntity("Successfully increased like count of lot: " + lid + ", for booking: " + bid, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
