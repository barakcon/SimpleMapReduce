package com.barakcohen;

import com.barakcohen.compute.CountingTask;
import com.barakcohen.compute.SiteCompute;
import com.barakcohen.resources.DataLoader;
import com.barakcohen.resources.EnDictionaryLoader;
import com.barakcohen.resources.AddressesLoader;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


public class App {
    private DataLoader addressLoader;
    private DataLoader enDictionaryLoader;
    private SiteCompute siteCompute;

    public App(DataLoader addressLoader, DataLoader enDictionaryLoader, SiteCompute siteCompute) {
        this.addressLoader = addressLoader;
        this.enDictionaryLoader = enDictionaryLoader;
        this.siteCompute = siteCompute;

    }

    public Map<String, Integer> run(int limitUrls, int threadCount)
            throws IOException, ExecutionException, InterruptedException {

        Set<String> vocabulary = new HashSet<>(this.enDictionaryLoader.load());
        List<String> urls = this.addressLoader.load();
        List<String> some_urls = urls.subList(0, limitUrls);

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Boolean>> futures = new ArrayList<>();

        for (String url : some_urls) {
            Future<Boolean> future = executorService.submit(() -> this.siteCompute.computeSite(url, map, vocabulary));
            futures.add(future);
        }

        for (Future<Boolean> f : futures) {
            f.get();
        }
        executorService.shutdown();
        executorService.awaitTermination(1L, TimeUnit.SECONDS);
        return map;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        App app = new App(new AddressesLoader(), new EnDictionaryLoader(), new CountingTask());
        Map<String, Integer> map = app.run(3, 10);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

}