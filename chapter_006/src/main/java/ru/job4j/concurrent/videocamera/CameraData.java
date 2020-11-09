package ru.job4j.concurrent.videocamera;

import java.io.Serializable;

public class CameraData implements Serializable {

    private static final long serialVersionUID = -4832447753680014708L;

    private int id;
    private String sourceDataUrl;
    private String tokenDataUrl;

    public int getId() {
        return id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }
}
