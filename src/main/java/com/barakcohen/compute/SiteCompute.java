package com.barakcohen.compute;

import java.util.Map;
import java.util.Set;

public interface SiteCompute {
    public Boolean computeSite(String url, Map<String, Integer> map, Set<String> vocabulary);
}
