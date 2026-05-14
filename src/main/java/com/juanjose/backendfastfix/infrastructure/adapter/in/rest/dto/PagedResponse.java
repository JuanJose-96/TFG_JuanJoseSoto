package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto;

import java.util.List;

public record PagedResponse<T>(
        List<T> content,
        int currentPage,
        int totalPages,
        long totalElements,
        boolean hasNext
) {
}
