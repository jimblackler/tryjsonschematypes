package net.jimblackler.tryjsonschematypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
  static void transfer(InputStream inputStream, OutputStream outputStream) throws IOException {
    byte[] buffer = new byte[16384];
    int length;
    while ((length = inputStream.read(buffer)) > 0) {
      outputStream.write(buffer, 0, length);
    }
  }
}
