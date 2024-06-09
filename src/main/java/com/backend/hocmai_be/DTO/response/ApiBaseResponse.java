package com.backend.hocmai_be.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Map;

@Data
public class ApiBaseResponse {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    private int status;
    @JsonProperty("data")
    private Map<String, Object> data;

}
