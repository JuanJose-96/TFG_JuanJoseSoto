package com.juanjose.backendfastfix.application.port.in.work;

import com.juanjose.backendfastfix.domain.model.Work;

public interface EditWorkUseCase {
    Work edit(Long workId, Work updateData);
}
