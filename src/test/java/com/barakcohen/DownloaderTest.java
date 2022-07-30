package com.barakcohen;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DownloaderTest {

    @Test
    public void testBasicDownload() throws IOException {
        String example = "https://www.engadget.com/2019-08-25-sony-and-yamaha-sc-1-sociable-cart.html";
        List<String> lines = Downloader.download(example);
        String expectedToContain = "As before, Sony feels the sensors eliminate the need for windows";
        assert(lines.stream().anyMatch(str -> str.contains(expectedToContain)));
    }

    @Test
    public void testBasicDownload2() throws IOException {
        String example = "https://www.engadget.com/2019-08-25-sony-and-yamaha-sc-1-sociable-cart.html";
        List<String> lines = Downloader.download2(example);
        String expectedToContain = "As before, Sony feels the sensors eliminate the need for windows";
        assert(lines.stream().anyMatch(str -> str.contains(expectedToContain)));
    }
}
