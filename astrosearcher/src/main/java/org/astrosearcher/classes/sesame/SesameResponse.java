package org.astrosearcher.classes.sesame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.constants.RegularExpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
public class SesameResponse {

    private String id;
    private List<String> aliases = new ArrayList<>();

    public SesameResponse(String response) {
        Pattern pattern = Pattern.compile(RegularExpressions.SESAME_VALID_RESPONSE);
        Matcher matcher = pattern.matcher(response);

//        System.out.println("Sesame response:\n" + response);

        if ( !matcher.matches() ) {
//            System.out.println("NOT valid sesame response!");
            return;
        }

        id = matcher.group(1);
//        System.out.println("    ID: " + id);

        // load all aliases
        pattern = Pattern.compile(RegularExpressions.SESAME_XML_ALIAS);
        matcher = pattern.matcher(response);
        while (matcher.find()) {
//            System.out.println("    Alias: " + matcher.group(1));
            aliases.add(matcher.group(1));
        }

//        for (int aliasCounter = 2; aliasCounter < matcher.groupCount(); aliasCounter++) {
//            aliases.add(matcher.group(aliasCounter));
//            System.out.println("    Alias: " + matcher.group(aliasCounter));
//        }
    }
}
