package net.jimblackler.tryjsonschematypes;

import static net.jimblackler.jsonschemafriend.DocumentUtils.loadJson;
import static net.jimblackler.tryjsonschematypes.ReaderUtils.getLines;

import com.google.appengine.repackaged.com.google.common.collect.HashMultiset;
import com.google.appengine.repackaged.com.google.common.collect.Multiset;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoStore {
  private static final FileSystem FILE_SYSTEM = FileSystems.getDefault();
  static private DemoStore singleton = null;
  private final List<String> demos = new ArrayList<>();
  private final Multiset<String> nameUse = HashMultiset.create();
  private final Map<String, Path> schemaByDemo = new HashMap<String, Path>();
  private final Map<String, Path> documentByDemo = new HashMap<String, Path>();

  public DemoStore() {
    Path path0 = FILE_SYSTEM.getPath("/SchemaStore").resolve("src");
    Path schemaPath = path0.resolve("schemas").resolve("json");
    Path testDir = path0.resolve("test");
    getLines(getClass().getResourceAsStream(testDir.toString()), resource -> {
      Path testSchema = schemaPath.resolve(resource + ".json");
      Path directoryPath = testDir.resolve(resource);
      if (!new File(getClass().getResource(directoryPath.toString()).getFile()).isDirectory()) {
        return;
      }
      getLines(getClass().getResourceAsStream(directoryPath.toString()), demo -> {
        Path testFile = directoryPath.resolve(demo);
        URL testDataUrl = getClass().getResource(testFile.toString());
        if (testDataUrl == null) {
          return;
        }
        nameUse.add(demo);
        int count = nameUse.count(demo);
        if (count > 1) {
          demo = demo + " (" + count + ")";
        }
        demos.add(demo);
        documentByDemo.put(demo, testFile);
        schemaByDemo.put(demo, testSchema);
      });
    });
  }

  static DemoStore getInstance() {
    if (singleton == null) {
      singleton = new DemoStore();
    }
    return singleton;
  }

  public InputStream getSchema(String demo) throws IOException {
    return getClass().getResourceAsStream(schemaByDemo.get(demo).toString());
  }

  public InputStream getDocument(String demo) throws IOException {
    return getClass().getResourceAsStream(documentByDemo.get(demo).toString());
  }

  public Iterable<String> getDemos() {
    return demos;
  }
}
