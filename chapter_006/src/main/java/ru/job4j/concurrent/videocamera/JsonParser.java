package ru.job4j.concurrent.videocamera;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static CameraData[] parseArray(InputStream is) {
        CameraData[] rsl = new CameraData[0];
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            rsl = mapper.readValue(bis, CameraData[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static CameraSourceData parseSource(InputStream is) {
        CameraSourceData csd = null;
        try (BufferedInputStream bis = new BufferedInputStream(
                is)) {
            csd = mapper.readValue(bis, CameraSourceData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csd;
    }

    public static TokenData parseToken(InputStream is) {
        TokenData td = null;
        try (BufferedInputStream bis = new BufferedInputStream(
                is)) {
            td = mapper.readValue(bis, TokenData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return td;
    }
}
