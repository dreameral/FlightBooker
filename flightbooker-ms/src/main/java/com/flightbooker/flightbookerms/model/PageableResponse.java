package com.flightbooker.flightbookerms.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableResponse<T> {
    private List<T> items;
    private int size;
    private int page;
    @JsonAnyGetter
    @JsonIgnore
    private Map<String, Object> otherProperties = new HashMap<>();

    public PageableResponse() {}

    public PageableResponse(List<T> items, int page, int size) {
        this.items = items;
        this.page = page;
        this.size = size;
    }
}
