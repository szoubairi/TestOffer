package com.example.demo.controller;

import java.net.URI;

import javax.validation.Valid;

import com.example.demo.model.UserDTO;
import com.example.demo.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;



@RestController
public class RegistrationController {
    
    @Autowired RegistrationService registrationService;

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  UserDTO.class))
        ), 
        @ApiResponse(
            responseCode = "404", 
            description = "User not found",
            content = @Content
        )
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        return registrationService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> NotFound.create(HttpStatus.NOT_FOUND, "Can't found user with id " + id,  HttpHeaders.EMPTY, null, null));

    }

    @Operation(summary = "Save a user")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)),
            headers = @Header(name = "Location", description = "Location of the created resource")
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Validation error",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody UserDTO user){
        UserDTO response = registrationService.register(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/users/{id}")
                        .buildAndExpand(response.getName())
                        .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
