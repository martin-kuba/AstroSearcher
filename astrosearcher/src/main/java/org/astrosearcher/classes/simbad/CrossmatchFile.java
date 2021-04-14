package org.astrosearcher.classes.simbad;

import org.astrosearcher.classes.Position;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.enums.simbad.SimbadArgType;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CrossmatchFile {
    private List<byte[]> cat1 = new ArrayList<>();


    public CrossmatchFile(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line = reader.readLine();

//            if (Limits.DEBUG) {
//                System.out.println("    Cross-match input file:");
//                System.out.println("        " + line);
//            }

            file.getBytes();
            file.getInputStream().read();

            if ( Position.isPosition(line) || !line.matches(RegularExpressions.FILE_STRICT_COLUMN_NAMES)) {
                // TODO: throw exception here, because 'ra, dec' line is missing....
                throw new IllegalArgumentException("file must contain line: 'ra,dec' at the beginning.");
            }
            reader.close();

            cat1.add("cat1.csv".getBytes());
            cat1.add(file.getBytes());
        } catch (IOException e) {
            System.out.println("There has been a problem with file: " + file.getName() +
                    ", " + e.getMessage());
        }
    }
}
