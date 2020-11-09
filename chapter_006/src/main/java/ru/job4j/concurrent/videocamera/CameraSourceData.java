package ru.job4j.concurrent.videocamera;

import java.io.Serializable;

public class CameraSourceData implements Serializable {

    private static final long serialVersionUID = -810876717398564468L;

    private String urlType;
    private String videoUrl;

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
