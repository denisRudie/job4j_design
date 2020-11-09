package ru.job4j.concurrent.videocamera;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class CameraConverter {

    private final ConcurrentLinkedQueue<CameraData> rawCameras = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<Integer, Camera> rslCameras = new ConcurrentHashMap<>();
    private final InputStream is;

    public CameraConverter(InputStream is) {
        this.is = is;
    }

    public ConcurrentHashMap<Integer, Camera> getRslCameras() {
        return rslCameras;
    }

    public void loadRawData() {
        rawCameras.addAll(Arrays.asList(
                JsonParser.parseArray(is)));
    }

    public void loadDetails() {
        ArrayList<CompletableFuture<Boolean>> futures = new ArrayList<>();

        while (!rawCameras.isEmpty()) {
            CameraData rawData = rawCameras.poll();
            futures.add(getSourceTask(rawData));
            futures.add(getTokenTask(rawData));
        }

        for (Future<Boolean> future : futures) {
            try {
                future.get(); // get will block until the future is done
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private CompletableFuture<Boolean> getSourceTask(CameraData cameraData) {
        return CompletableFuture.supplyAsync(() -> {
            int id = cameraData.getId();

            try {
                rslCameras.putIfAbsent(id, new Camera(id));
                Camera camera = rslCameras.get(id);
                CameraSourceData csd = JsonParser.parseSource(
                        new URL(cameraData.getSourceDataUrl())
                                .openStream());
                camera.setUrlType(csd.getUrlType());
                camera.setVideoUrl(csd.getVideoUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
           return true;
        });
    }

    private CompletableFuture<Boolean> getTokenTask(CameraData cameraData) {
        return CompletableFuture.supplyAsync(() -> {
            int id = cameraData.getId();

            try {
                rslCameras.putIfAbsent(id, new Camera(id));
                Camera camera = rslCameras.get(id);
                TokenData td = JsonParser.parseToken(
                        new URL(cameraData.getTokenDataUrl())
                                .openStream());
                camera.setTtl(td.getTtl());
                camera.setValue(td.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        });
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CameraConverter converter = new CameraConverter(new URL("http://www.mocky" +
                ".io/v2/5c51b9dd3400003252129fb5").openStream());
        converter.loadRawData();
        converter.loadDetails();
        converter.getRslCameras().values().forEach(System.out::println);
    }
}
