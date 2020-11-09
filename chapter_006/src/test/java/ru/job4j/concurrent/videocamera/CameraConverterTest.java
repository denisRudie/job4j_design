package ru.job4j.concurrent.videocamera;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class CameraConverterTest {

    /**
     * Parse url http://www.mocky.io/v2/5c51b9dd3400003252129fb5.
     *
     * @throws IOException throws.
     */
    @Test
    public void whenParseRawDataThenGetNewFormatData() throws IOException {
        CameraConverter converter = new CameraConverter(new URL("http://www.mocky" +
                ".io/v2/5c51b9dd3400003252129fb5").openStream());
        converter.loadRawData();
        converter.loadDetails();

        HashSet<Integer> expected = new HashSet<>();
        ConcurrentHashMap<Integer, Camera> result = converter.getRslCameras();

        final ObjectNode[] node = new ObjectMapper().readValue(new URL("http://www.mocky" +
                ".io/v2/5c51b9dd3400003252129fb5").openStream(), ObjectNode[].class);

        for (ObjectNode jsonNodes : node) {
            expected.add(jsonNodes.get("id").asInt());
        }

        assertEquals(expected, result.keySet());
    }
}