package org.astrosearcher.classes.simbad;

import lombok.AllArgsConstructor;
import org.astrosearcher.enums.cds.simbad.SimbadArgType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Class provides basic functionality for easier building of URL request
 * for Simbad server - easier chaining of arguments (parameters) in URL.
 *
 * @author Ľuboslav Halama
 */
@AllArgsConstructor
public class SimbadArg {

    private SimbadArgType type;
    private Object value;

    @Override
    public String toString() {
        return "&" + type.toString() + "=" + URLEncoder.encode(value.toString(), StandardCharsets.UTF_8);
    }
}
