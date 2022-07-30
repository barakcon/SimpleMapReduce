package com.barakcohen.compute;

import com.barakcohen.Downloader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CountingTask implements SiteCompute {

    public Boolean computeSite(String url, Map<String, Integer> map, Set<String> vocabulary) {
        List<String> lines = null;
        try {
            lines = Downloader.download(url);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (vocabulary.contains(word)) {
                    map.putIfAbsent(word, 0);
                    map.computeIfPresent(word, (key, value) -> value + 1);
                }
            }
        }
        System.out.println("Done future" + Thread.currentThread().getId());
        return true;
    }
}
