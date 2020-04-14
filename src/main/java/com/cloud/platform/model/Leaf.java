package com.cloud.platform.model;

import lombok.Data;

@Data
public class Leaf {
    private String label;
    public Leaf(String label){
        this.label = label;
    }
}
