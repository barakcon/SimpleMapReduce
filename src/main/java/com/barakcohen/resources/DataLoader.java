package com.barakcohen.resources;

import java.io.IOException;
import java.util.List;

public interface DataLoader {
    public List<String> load() throws IOException;
}
