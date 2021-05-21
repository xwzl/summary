package com.turing.java.jvm.concurrent.atomic;

import lombok.Data;

@Data
public class Turing {

    private Integer sequence;

    public Turing(Integer seq) {
        sequence = seq;
    }
}
