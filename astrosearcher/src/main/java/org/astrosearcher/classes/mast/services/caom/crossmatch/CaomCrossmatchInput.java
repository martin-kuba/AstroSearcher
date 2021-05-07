package org.astrosearcher.classes.mast.services.caom.crossmatch;

import lombok.Getter;
import org.astrosearcher.classes.ArgType;
import org.astrosearcher.classes.Position;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.MASTConstants;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents parsed (data read) from uploaded file for crossmatch query on MAST.
 *
 * @author Ľuboslav Halama
 */
@Getter
public class CaomCrossmatchInput {

    private List<ArgType>  fields = new ArrayList<>();
    private List<Position> data   = new ArrayList<>();

    public CaomCrossmatchInput(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line = reader.readLine();

            if (AppConfig.DEBUG) {
                System.out.println("        Cross-match input file:");
                System.out.println("            " + line);
            }

            // set fields for RA, DEC
            fields.add(new ArgType(MASTConstants.DEFAULT_RA_COLUMN_NAME, MASTConstants.RA_TYPE));
            fields.add(new ArgType(MASTConstants.DEFAULT_DEC_COLUMN_NAME, MASTConstants.DEC_TYPE));

            // if file does not include descriptive line first
            if ( !line.matches(RegularExpressions.FILE_STRICT_COLUMN_NAMES)) {
                throw new IllegalArgumentException("file must contain line: 'ra,dec' at the beginning.");
            }

            // read data
            while ( (line = reader.readLine()) != null ) {
                if (AppConfig.DEBUG) {
                    System.out.println("            " + line);
                }
                data.add(new Position(line));
            }

        } catch (IOException e) {
            System.out.println("There has been a problem with file: " + file.getName() +
                    ", " + e.getMessage());
        }
    }
}
