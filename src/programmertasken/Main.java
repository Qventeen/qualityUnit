package programmertasken;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) return;

        Path path = Paths.get(args[0]);
        path = path.toAbsolutePath();
        if(!Files.exists(path) || !Files.isRegularFile(path)){
            return;
        }

        DataParser parser = new DataParser();
        parser.parseFile(path);
    }

}
