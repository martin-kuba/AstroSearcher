package org.astrosearcher.classes.vizier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.enums.vizier.VizierArgType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
