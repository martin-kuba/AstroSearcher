package org.astrosearcher.classes.simbad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimbadRequestObject extends RequestObject {

    private static final String CONNECTION_URL = "http://simbad.u-strasbg.fr/simbad/";
    private static final String FORMAT  = "output.format=votable";

    private static final String DEFAULT_RADIUS_UNIT = "deg";

    private SimbadServices service;
    private List<SimbadArg> args = new ArrayList<>();

    public SimbadRequestObject(String id) {
        this.service = SimbadServices.SIMBAD_ID;
        args.add(new SimbadArg(SimbadArgType.ID, id));
    }

    public SimbadRequestObject(PositionInput input) {
        this.service = SimbadServices.SIMBAD_COORDINATES;
        args.add(new SimbadArg(SimbadArgType.COORDINATES, input.getPosition()));
        args.add(new SimbadArg(SimbadArgType.RADIUS, input.getRadius()));
        args.add(new SimbadArg(SimbadArgType.RADIUS_UNIT, DEFAULT_RADIUS_UNIT));
    }

    @Override
    public String send() {
        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
//        System.out.println("CONNECTION URL: " + CONNECTION_URL + service + "?");
        return new URL(CONNECTION_URL + service + "?");
    }

    @Override
    public byte[] getParamsAsBytes() {

        StringBuilder params = new StringBuilder();
        for (SimbadArg arg : args) {
            params.append(arg.toString());
        }

//        System.out.println("CONNECTION PARAMETERS: " + FORMAT + params.toString());
        return (FORMAT + params.toString()).getBytes();
    }
}
