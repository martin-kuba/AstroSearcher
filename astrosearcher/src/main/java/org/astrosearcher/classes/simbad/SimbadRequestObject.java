package org.astrosearcher.classes.simbad;

import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.RegularExpressions;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.enums.cds.simbad.SimbadArgType;
import org.astrosearcher.enums.cds.simbad.SimbadServices;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents request object which is used in URL request sent to Simbad
 * (cds) server based on information in:
 * ->   http://simbad.u-strasbg.fr/Pages/guide/sim-url.htx   <-
 *
 * Class provides basic properties for sending request to Simbad server as well
 * as main functionality for sending given request by our web application
 * (implementation of abstract methods from abstract class RequestObject).
 *
 * @author Ľuboslav Halama
 */
public class SimbadRequestObject extends RequestObject {

    private SimbadServices service;
    private String          format = SimbadConstants.DEFAULT_FORMAT;
    private List<SimbadArg> args = new ArrayList<>();

    private SimbadRequestObject(SimbadServices service) {
        this.service = service;
    }

    public SimbadRequestObject(SimbadServices service, SearchFormInput input, String format) {
        this(service, input);
        this.format = format;
    }

    public SimbadRequestObject(SimbadServices service, SearchFormInput input) {
        this(service);
        switch (service) {
            case SIMBAD_ID:
                args.add(new SimbadArg(SimbadArgType.ID, input.getSearchInput()));
                args.add(new SimbadArg(SimbadArgType.MEASURES_DISP_T, "A"));
                args.add(new SimbadArg(SimbadArgType.MEASURES_CATS, ""));
                break;
            case SIMBAD_COORDINATES:

                if (RegularExpressions.isIAUFormat(input.getSearchInput())) {
                    args.add(new SimbadArg(SimbadArgType.COORDINATES, input.getSearchInput()));
                } else {
                    PositionInput position = new PositionInput(input.getSearchInput());
                    args.add(new SimbadArg(SimbadArgType.COORDINATES, position.getPosition()));
                }

                args.add(new SimbadArg(SimbadArgType.RADIUS, input.getRadius()));
                args.add(new SimbadArg(SimbadArgType.RADIUS_UNIT, SimbadConstants.DEFAULT_RADIUS_UNIT));
                args.add(new SimbadArg(SimbadArgType.OUTPUT_LIMIT, input.getPagesize()));
                break;
            default:
                throw new IllegalArgumentException(ExceptionMSG.NO_SERVICE_PROVIDED_BY_SIMBAD_EXCEPTION
                        + input.getSearchBy());
        }
    }

    @Override
    public String send() {
        if ( AppConfig.DEBUG ) {
            System.out.println("\n    >>> Starting to query SIMBAD...");
        }

        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
//        System.out.println("CONNECTION URL: " + CONNECTION_URL + service + "?");
        return new URL(SimbadConstants.CONNECTION_URL + service + "?");
    }

    @Override
    public byte[] getParamsAsBytes() {

        StringBuilder params = new StringBuilder();
        for (SimbadArg arg : args) {
            params.append(arg.toString());
        }

        if (AppConfig.DEBUG && AppConfig.DEBUG_SIMBAD_REQUEST) {
            System.out.println();
            System.out.println("            Parameters = " + format + params.toString());
        }

        return (format + params.toString()).getBytes();
//                (SimbadConstants.DEFAULT_FORMAT + params.toString()).getBytes();
    }
}
