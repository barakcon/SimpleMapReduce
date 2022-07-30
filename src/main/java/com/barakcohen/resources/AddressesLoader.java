package com.barakcohen.resources;
import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AddressesLoader implements DataLoader {

  public List<String> load() throws IOException {
    URL file = new URL("https://nx-public.s3-eu-west-1.amazonaws.com/Interview/endg-urls");
    ReadableByteChannel rbc = Channels.newChannel(file.openStream());

    // download words
    Path tmpPath = Files.createTempFile("urls", ".txt");
    try (FileOutputStream fos = new FileOutputStream(tmpPath.toFile())){
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    // prepare result
    List<String> res = Files.readAllLines(tmpPath);
    Files.deleteIfExists(tmpPath);
    return res;
  }
}
