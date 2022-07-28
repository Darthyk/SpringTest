package com.darthyk.springtest.service;

import lombok.Data;

@Data
public class SearchCriteria<T> {
    private String key;
    private T value;

    public SearchCriteria(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
