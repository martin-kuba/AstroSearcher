package org.astrosearcher.classes.mast.services.caom.crossmatch;

import lombok.Getter;
import org.astrosearcher.classes.ArgType;
import org.astrosearcher.classes.Position;
import org.astrosearcher.classes.constants.MASTConstants;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents parsed (data read) from uploaded file for crossmatch
 * query on MAST.
 *
 * @author Ľuboslav Halama
 */
@Getter
public class CaomCrossmatchInput {

    private static final Logger log = LoggerFactory.getLogger(CaomCrossmatchInput.class);

    private List<ArgType> fields = new ArrayList<>();
    private List<Position> data = new ArrayList<>();

    public CaomCrossmatchInput(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line = reader.readLine();
            log.debug("        Cross-match input file:");
            log.debug("            {}", line);

            // set fields for RA, DEC
            fields.add(new ArgType(MASTConstants.DEFAULT_RA_COLUMN_NAME, MASTConstants.RA_TYPE));
            fields.add(new ArgType(MASTConstants.DEFAULT_DEC_COLUMN_NAME, MASTConstants.DEC_TYPE));

            // if file does not include descriptive line first
            if (!line.matches(RegularExpressions.FILE_STRICT_COLUMN_NAMES_LINE)) {
                throw new IllegalArgumentException("file must contain line: 'ra,dec' at the beginning.");
            }

            // read data
            while ((line = reader.readLine()) != null) {
                log.debug("            {}", line);
                data.add(new Position(line));
            }

        } catch (IOException e) {
            log.error("There has been a problem with file: {}, {}", file.getName(), e.getMessage());
        }
    }
}
