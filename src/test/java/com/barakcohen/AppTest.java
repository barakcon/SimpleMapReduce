package com.barakcohen;

import com.barakcohen.compute.CountingTask;
import com.barakcohen.resources.AddressesLoader;
import com.barakcohen.resources.DataLoader;
import com.barakcohen.resources.EnDictionaryLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void SanityTest() throws IOException, ExecutionException, InterruptedException {
        App app = new App(new AddressesLoader(), new EnDictionaryLoader(), new CountingTask());

        Map<String, Integer> result = app.run(1, 1);

        assertNotNull(result);
        assertEquals(result.size(), 179);
        assertEquals(result.get("slightly"), 4);
        assertEquals(result.get("path"), 1);
        assertEquals(result.get("an"), 12);
    }

//    @Test
//    public void QuickSanityTest() throws IOException, ExecutionException, InterruptedException {
//        DataLoader al = new DataLoader() {
//
//            @Override
//            public List<String> load() throws IOException {
//                return null;
//            }
//        };
//
//        DataLoader dl = new DataLoader() {
//
//            @Override
//            public List<String> load() throws IOException {
//                return null;
//            }
//        };
//
//        DataLoader al = new DataLoader() {
//
//            @Override
//            public List<String> load() throws IOException {
//                return null;
//            }
//        };
//
//        App app = new App(al, new EnDictionaryLoader(), new CountingTask());
//
//        Map<String, Integer> result = app.run(1, 1);
//
//        assertNotNull(result);
//        assertEquals(result.size(), 179);
//        assertEquals(result.get("slightly"), 4);
//        assertEquals(result.get("path"), 1);
//        assertEquals(result.get("an"), 12);
//    }
}
