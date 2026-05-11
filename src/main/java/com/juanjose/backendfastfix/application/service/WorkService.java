package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.work.CreateAWorkUseCase;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.WorkRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.domain.model.Work;
import org.springframework.stereotype.Service;


@Service
public class WorkService implements CreateAWorkUseCase {
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
}
