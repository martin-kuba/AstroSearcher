package org.astrosearcher.classes.vizier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.enums.cds.vizier.VizierArgType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Class provides basic functionality for easier building of URL request
 * for Vizier server - easier chaining of arguments (parameters) in URL.
 *
 * @author Ľuboslav Halama
 */
@AllArgsConstructor
@Getter
public class VizierArg {
    private VizierArgType type;
    private Object        value;

    @Override
    public String toString() {
        return "&" + type + URLEncoder.encode(value.toString(), StandardCharsets.UTF_8);
    }
}
