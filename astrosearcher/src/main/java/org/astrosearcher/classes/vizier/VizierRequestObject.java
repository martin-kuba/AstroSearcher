package org.astrosearcher.classes.vizier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.astrosearcher.TomcatConfig;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.AppConfig;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.constants.cds.VizierConstants;
import org.astrosearcher.enums.VizierCatalogueSearch;
import org.astrosearcher.enums.cds.vizier.VizierArgType;
import org.astrosearcher.enums.cds.vizier.VizierServices;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents request object which is used in URL request sent to Vizier
 * (CDS) server.
 *
 * Class provides basic properties for sending request to Vizier server as well
 * as main functionality for sending the given request by our web application
 * (implementation of abstract methods from abstract class RequestObject).
 *
 * @author Ľuboslav Halama
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VizierRequestObject extends RequestObject {

    private static final Logger log = LoggerFactory.getLogger(TomcatConfig.class);

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
                args.add(new VizierArg(VizierArgType.RADIUS, input.getRadius()));
                args.add(new VizierArg(VizierArgType.RADIUS_UNIT, VizierConstants.DEFAULT_RADIUS_UNIT));
                break;
            default:
                throw new IllegalArgumentException(ExceptionMSG.NO_SERVICE_PROVIDED_BY_VIZIER_EXCEPTION
                        + input.getSearchBy());
        }

        // which catalogues should be queried
        args.add(new VizierArg(
                VizierCatalogueSearch.valueOf(input.getVizierCatalogueSearchBy()).getArgType(),
                input.getVizierCat())
        );

        // output limit
        args.add(new VizierArg(VizierArgType.OUTPUT_LIMIT, input.getPagesize()));
    }

    @Override
    public String send() {
        if ( AppConfig.DEBUG ) {
            log.debug("\n    >>> Starting to query VIZIER...\n");
        }

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
