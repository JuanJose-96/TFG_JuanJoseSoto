package com.juanjose.backendfastfix.application.port.in.work;

import com.juanjose.backendfastfix.domain.model.Work;

import java.util.List;

public interface GetTechnicianWorksUseCase {
    List<Work> getByTechnicianId(Long technicianId);
}
