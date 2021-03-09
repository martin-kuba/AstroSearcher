package org.astrosearcher.classes.simbad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class SimbadRequestObject extends RequestObject {

    private static final String CONNECTION_URL = "http://simbad.u-strasbg.fr/simbad/";
    private static final String FORMAT  = "output.format=votable";

    private SimbadServices service;
    private double ra;
    private double dec;
    private double radius;

    public SimbadRequestObject(SimbadServices service) {
        this.service = service;
    }

    @Override
    public String send() {
        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(CONNECTION_URL + service + "?");
    }

    @Override
    public byte[] getParamsAsBytes() {
        return (FORMAT).getBytes();
    }
}
