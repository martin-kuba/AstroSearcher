package org.astrosearcher.classes.mast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.utilities.ConnectionUtils;

public class MastRequestObject extends RequestObject {

    @JsonIgnore
    private static final String CONNECTION_URL = "https://mast.stsci.edu/api/v0/invoke?";
    @JsonIgnore
    private static final String REQUEST_PARAMS_PREFIX = "request=";

    @JsonIgnore
    private static final Gson gson = new Gson();

    // TODO: finish implementation for the rest of object attributes
    private String service;
    private Map<String, Object> params = new HashMap<>();
    private String format = "json";
    //private Object data;
    //private String fileName;
    //private int timeout = 30;
    //private boolean clearCache = false;
    //private boolean removeCache = false;
    //private boolean removeNullColumns = true;
    //private String page;
    //private int pageSize = 100;

    public MastRequestObject(MastServices service, String name) {
        this.service = service.toString();
        params.put("input", name);
        params.put("format", "json");
    }

    public MastRequestObject(MastServices service, double ra, double dec) {
        this.service = service.toString();
        params.put("ra", ra);
        params.put("dec", dec);
    }

    public MastRequestObject(MastServices service, double ra, double dec, double radius) {
        this.service = service.toString();
        params.put("ra", ra);
        params.put("dec", dec);
        params.put("radius", radius);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public String send() {
        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(CONNECTION_URL);
    }

    @Override
    public byte[] getParamsAsBytes() {
        return (REQUEST_PARAMS_PREFIX + gson.toJson(this)).getBytes();
    }
}
