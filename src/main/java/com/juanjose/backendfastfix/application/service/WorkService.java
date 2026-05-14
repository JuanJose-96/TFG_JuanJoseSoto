package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.work.CreateAWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.DeleteWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.EditWorkUseCase;
import com.juanjose.backendfastfix.application.port.in.work.GetTechnicianWorksUseCase;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.WorkRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.domain.exception.WorkNotFoundException;
import com.juanjose.backendfastfix.domain.model.Work;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WorkService implements CreateAWorkUseCase, EditWorkUseCase,
        GetTechnicianWorksUseCase, DeleteWorkUseCase {
    private final WorkRepositoryPort workRepositoryPort;
    private final TechnicianRepositoryPort technicianRepositoryPort;

    public WorkService(WorkRepositoryPort workRepositoryPort, TechnicianRepositoryPort technicianRepositoryPort) {
        this.workRepositoryPort = workRepositoryPort;
        this.technicianRepositoryPort = technicianRepositoryPort;
    }



    @Override
    public Work create(Work work) {
        technicianRepositoryPort.findById(work.getTechnicianId())
                .orElseThrow(() -> new TechnicianNotFoundException(work.getTechnicianId()));

        return workRepositoryPort.save(work);
    }

    @Override
    public void delete(Long workId) {
        workRepositoryPort.deleteById(workId);

    }

    @Override
    public Work edit(Long workId, Work updateData) {
        Work work = workRepositoryPort.findById(workId)
                .orElseThrow(()-> new WorkNotFoundException(workId));

        Work updatedWork = work.toBuilder()
                .clientId(updateData.getClientId())
                .clientName(updateData.getClientName())
                .clientSurname(updateData.getClientSurname())
                .clientProvince(updateData.getClientProvince())
                .clientCity(updateData.getClientCity())
                .serviceDate(updateData.getServiceDate())
                .totalAmount(updateData.getTotalAmount())
                .serviceDescription(updateData.getServiceDescription())
                .build();

        return workRepositoryPort.save(updatedWork);
    }

    @Override
    public List<Work> getByTechnicianId(Long technicianId) {
        technicianRepositoryPort.findById(technicianId)
                .orElseThrow(() -> new TechnicianNotFoundException(technicianId));

        return workRepositoryPort.findByTechnicianId(technicianId);
    }
}
