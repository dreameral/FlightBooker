package com.flightbooker.flightbookerms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableResponse<T> {
    private List<T> items;
    private int size;
    private int page;
    @JsonUnwrapped
    private Map<String, Object> otherProperties;

    public PageableResponse() {}

    public PageableResponse(List<T> items, int page, int size) {
        this.items = items;
        this.page = page;
        this.size = size;
    }
}
