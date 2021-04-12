package org.astrosearcher.utilities;

import org.astrosearcher.classes.sesame.SesameRequestObject;
import org.astrosearcher.classes.sesame.SesameResponse;

import java.util.ArrayList;
import java.util.List;

public class SesameSearchEngine {

    public static SesameResponse findAllAliasesForId(String id) {
        String response = new SesameRequestObject(id).send();

        return response == null ? new SesameResponse() : new SesameResponse(response);
    }
}
