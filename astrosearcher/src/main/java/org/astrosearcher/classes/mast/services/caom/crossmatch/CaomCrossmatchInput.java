package org.astrosearcher.classes.mast.services.caom.crossmatch;

import lombok.Getter;
import org.astrosearcher.classes.ArgType;
import org.astrosearcher.classes.Position;
import org.astrosearcher.classes.constants.MASTConstants;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class CaomCrossmatchInput {

    private List<ArgType>  fields = new ArrayList<>();
    private List<Position> data   = new ArrayList<>();

    public CaomCrossmatchInput(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line = reader.readLine();
            System.out.println("line: " + line);
//            if ( !Position.isPosition(line) ) {
//                Matcher matcher = Pattern.compile(RegularExpressions.FILE_COLUMNS_NAMES).matcher(line);
//                fields.add(new ArgType(matcher.group(1), MASTConstants.RA_TYPE));
//                fields.add(new ArgType(matcher.group(2), MASTConstants.DEC_TYPE));
//            } else {
//                fields.add(new ArgType(MASTConstants.DEFAULT_RA_COLUMN_NAME, MASTConstants.RA_TYPE));
//                fields.add(new ArgType(MASTConstants.DEFAULT_DEC_COLUMN_NAME, MASTConstants.DEC_TYPE));
//            }

            fields.add(new ArgType(MASTConstants.DEFAULT_RA_COLUMN_NAME, MASTConstants.RA_TYPE));
            fields.add(new ArgType(MASTConstants.DEFAULT_DEC_COLUMN_NAME, MASTConstants.DEC_TYPE));

            if ( Position.isPosition(line) ) {
                data.add(new Position(line));
            }

            while ( (line = reader.readLine()) != null ) {
                System.out.println("line: " + line);
                data.add(new Position(line));
            }

        } catch (IOException e) {
            System.out.println("There has been a problem with file: " + file.getName() +
                    ", " + e.getMessage());
        }
    }
}
