package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller;

import com.juanjose.backendfastfix.application.port.in.work.CreateAWorkUseCase;
import com.juanjose.backendfastfix.domain.model.Work;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.CreateWorkRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.WorkResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.WorkRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
public class WorkController {
    private final CreateAWorkUseCase createAWorkUseCase;

    public WorkController(CreateAWorkUseCase createAWorkUseCase) {
        this.createAWorkUseCase = createAWorkUseCase;
    }

    @PostMapping
    public ResponseEntity<WorkResponse> create(@Valid @RequestBody CreateWorkRequest request){
        Work workSaved = createAWorkUseCase
                .create(WorkRestMapper.createToDomain(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WorkRestMapper.fromDomain(workSaved));

    }
}
