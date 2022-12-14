package com.barakcohen.resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnDictionaryLoader implements DataLoader {

  public List<String> load() throws IOException {
    URL website = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
    ReadableByteChannel rbc = Channels.newChannel(website.openStream());

    // download words
    Path tmpPath = Files.createTempFile("words", ".txt");
    try (FileOutputStream fos = new FileOutputStream(tmpPath.toFile())){
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    // prepare result
    List<String> res =Files.readAllLines(tmpPath);
    Files.deleteIfExists(tmpPath);
    return res;
  }
}