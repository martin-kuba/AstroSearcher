package org.astrosearcher.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.MastServices;
import org.astrosearcher.classes.mast.MastRequestObject;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;
import org.astrosearcher.classes.mast.services.name.lookup.ResponseForReqByName;

import java.util.ArrayList;
import java.util.List;

public class MASTSearchEngine {

    public static List<PositionInput> resolvePositionByNameOrID(String input) {

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


    public static ResponseForReqByPos findAllByPosition(double ra, double dec, double radius) {

        String response = new MastRequestObject(MastServices.MAST_CAOM_CONE, ra, dec, radius).send();

        return response == null ? null : new Gson().fromJson(response, ResponseForReqByPos.class);
    }

}