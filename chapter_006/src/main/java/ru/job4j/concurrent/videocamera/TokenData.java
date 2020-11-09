package ru.job4j.concurrent.videocamera;

import java.io.Serializable;

public class TokenData implements Serializable {

    private static final long serialVersionUID = 1903822377312798946L;

    private String value;
    private int ttl;

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }
}
