package org.astrosearcher.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.MastServices;
import org.astrosearcher.classes.mast.MastRequestObject;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.classes.mast.services.name.lookup.ResponseForReqByName;
import org.astrosearcher.models.SearchFormInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Class serves as inter-level between general SearchEngine class and ConnectionUtils class.
 *
 * Class provides usage of RequestObject needed for constructing and sending of request as well as parsing of response
 * acquired from MAST server.
 *
 * @author Ľuboslav Halama
 */
public class MASTSearchEngine {

    private static List<PositionInput> resolvePositionByNameOrID(SearchFormInput input) {

        List<PositionInput> resolved = new ArrayList<>();
        String response = new MastRequestObject(MastServices.MAST_NAME_LOOKUP, input).send();

        if (response == null) {
            return null;
        }

        double radius;
        // in case parsing would not be valid
        try {
            ResponseForReqByName resp = new Gson().fromJson(response, ResponseForReqByName.class);

            for (JsonObject obj : resp.getResolvedCoordinate()) {

                // TODO: create new general class for constants like this radius
                radius = 0.2;  // default radius;
                if ( obj.has("radius") && !obj.get("radius").isJsonNull() ) {
                    radius = obj.get("radius").getAsDouble();
                }

                resolved.add(new PositionInput(
                        obj.get("ra").getAsDouble(),
                        obj.get("decl").getAsDouble(),
                        radius
                ));
            }
        } catch (Exception e) {
//            System.out.println("Exception caught:\n" + e);
        }

        return resolved;
    }

    public static ResponseForReqByPos findAllByID(SearchFormInput input) {
        List<PositionInput> resolved = resolvePositionByNameOrID(input);
        return resolved.isEmpty() ? null : findAllByPosition(resolved.get(0), input);
    }

    public static ResponseForReqByPos findAllByPosition(SearchFormInput input) {
        String response = new MastRequestObject(MastServices.MAST_CAOM_CONE, input).send();
        return response == null ? null : new Gson().fromJson(response, ResponseForReqByPos.class);
    }

    public static ResponseForReqByPos findAllByPosition(PositionInput position, SearchFormInput input) {
        String response = new MastRequestObject(MastServices.MAST_CAOM_CONE, position, input).send();
        return response == null ? null : new Gson().fromJson(response, ResponseForReqByPos.class);
    }

}