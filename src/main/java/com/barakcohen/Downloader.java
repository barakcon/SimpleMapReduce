package com.barakcohen;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Downloader {
    public static List<String> download(String url) throws IOException, FileNotFoundException {
        try {
            URL website = new URL(url);
            HttpURLConnection myURLConnection = (HttpURLConnection)website.openConnection();
            myURLConnection.setRequestProperty("User-Agent", "blabla");
            ReadableByteChannel rbc = Channels.newChannel(myURLConnection.getInputStream());
            Path tmpPath = Files.createTempFile("someifle", ".txt");
            try (FileOutputStream fos = new FileOutputStream(tmpPath.toFile())) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            List<String> lines = new ArrayList<>(Files.readAllLines(tmpPath));
            Files.deleteIfExists(tmpPath);
            return lines;
        } catch(FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static List<String> download2(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create()
                .setRedirectStrategy(new DefaultRedirectStrategy()).build();
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("User-Agent", "blabla");
        HttpResponse httpresponse = httpclient.execute(httpget);
        Scanner sc = new Scanner(httpresponse.getEntity().getContent());
        List<String> lines = new ArrayList<>();
        while(sc.hasNext()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }
}
