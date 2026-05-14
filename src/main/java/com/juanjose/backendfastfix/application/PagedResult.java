package com.juanjose.backendfastfix.application;

import java.util.List;

public class PagedResult <T>{
    private final List<T> content;
    private final int currentPage;
    private final int totalPages;
    private final long totalElements;
    private final boolean hasNext;

    public PagedResult(List<T> content, int currentPage, int totalPages, long totalElements, boolean hasNext) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.hasNext = hasNext;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isHasNext() {
        return hasNext;
    }
}
