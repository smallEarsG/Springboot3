package org.example.h5.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestModel {
    @JsonProperty("code")
    private String code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private String data;
}
