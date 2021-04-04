package org.astrosearcher.classes.vizier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.constants.VizierConstants;
import org.astrosearcher.enums.VizierCatalogueSearch;
import org.astrosearcher.enums.vizier.VizierArgType;
import org.astrosearcher.enums.vizier.VizierServices;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VizierRequestObject extends RequestObject {

    private String format = VizierConstants.FORMAT_VOTABLE;
    private List<VizierArg> args = new ArrayList<>();

    public VizierRequestObject(VizierServices service, SearchFormInput input) {

        switch (service) {
            case VIZIER_ID:
                args.add(new VizierArg(VizierArgType.TARGET, input.getSearchInput()));
                break;
            case VIZIER_COORDINATES:
                PositionInput position = new PositionInput(input.getSearchInput());

                args.add(new VizierArg(VizierArgType.TARGET, position.getPosition()));
                args.add(new VizierArg(VizierArgType.RADIUS, position.getRadius()));
                args.add(new VizierArg(VizierArgType.RADIUS_UNIT, VizierConstants.DEFAULT_RADIUS_UNIT));
                break;
            default:
                throw new IllegalArgumentException(ExceptionMSG.NO_SERVICE_PROVIDED_BY_VIZIER_EXCEPTION
                        + input.getSearchBy());
        }

        args.add(new VizierArg(
                VizierCatalogueSearch.valueOf(input.getVizierCatalogueSearchBy()).getArgType(),
                input.getVizierCat())
        );
        args.add(new VizierArg(VizierArgType.OUTPUT_LIMIT, input.getPagesize()));


        // TODO: edit to work for all search types, now works only for position

    }

    @Override
    public String send() {
//        StringBuilder params = new StringBuilder();
//        for (VizierArg arg : args) {
//            params.append(arg.toString());
//        }
//
//        System.out.println("URL: "
//                + VizierConstants.CONNECTION_URL + format
//                + "?" + params.toString()
//        );
        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(VizierConstants.CONNECTION_URL + format + "?");
    }

    @Override
    public byte[] getParamsAsBytes() {
        StringBuilder params = new StringBuilder();
        for (VizierArg arg : args) {
            params.append(arg.toString());
        }

        return params.toString().getBytes();
    }
}
