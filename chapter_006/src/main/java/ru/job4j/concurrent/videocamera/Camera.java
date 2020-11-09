package ru.job4j.concurrent.videocamera;

import java.util.Objects;

public class Camera {

    private final int id;
    private String urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public Camera(int id) {
        this.id = id;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getId() {
        return id;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return id == camera.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", urlType='" + urlType + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", value='" + value + '\'' +
                ", ttl=" + ttl +
                '}';
    }
}
