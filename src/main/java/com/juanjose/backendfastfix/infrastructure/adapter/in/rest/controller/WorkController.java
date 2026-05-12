package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller;

import com.juanjose.backendfastfix.application.port.in.work.CreateAWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.DeleteWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.EditWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.GetTechnicianWorksUseCase;
import com.juanjose.backendfastfix.domain.model.Work;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.CreateWorkRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.EditWorkRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.work.WorkResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.WorkRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/works")
public class WorkController {
    private final CreateAWorkUseCase createAWorkUseCase;
    private final GetTechnicianWorksUseCase getTechnicianWorksUseCase;
    private final EditWorkUseCase editWorkUseCase;
    private final DeleteWorkUseCase deleteWorkUseCase;

    public WorkController(CreateAWorkUseCase createAWorkUseCase, GetTechnicianWorksUseCase getTechnicianWorksUseCase, EditWorkUseCase editWorkUseCase, DeleteWorkUseCase deleteWorkUseCase) {
        this.createAWorkUseCase = createAWorkUseCase;
        this.getTechnicianWorksUseCase = getTechnicianWorksUseCase;
        this.editWorkUseCase = editWorkUseCase;
        this.deleteWorkUseCase = deleteWorkUseCase;
    }

    @PostMapping
    public ResponseEntity<WorkResponse> create(@Valid @RequestBody CreateWorkRequest request){
        Work workSaved = createAWorkUseCase
                .create(WorkRestMapper.createToDomain(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WorkRestMapper.fromDomain(workSaved));

    }

    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<List<WorkResponse>> getWorksByTechnician(@PathVariable Long technicianId){
        List<WorkResponse> works = getTechnicianWorksUseCase.getByTechnicianId(technicianId)
                .stream()
                .map(WorkRestMapper::fromDomain)
                .toList();

        return ResponseEntity.ok(works);
    }

    @DeleteMapping("/{workId}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long workId){
        deleteWorkUseCase.delete(workId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{workId}")
    public ResponseEntity<WorkResponse> editWork(@PathVariable Long workId,
                                                 @Valid @RequestBody EditWorkRequest request){

        Work updatedWork = editWorkUseCase
                .edit(workId, WorkRestMapper.editToDomain(request));

        return ResponseEntity.ok(WorkRestMapper.fromDomain(updatedWork));
    }


}
