package org.astrosearcher.classes.mast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.enums.SearchType;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;

/**
 * Class represents request object which is used in URL request sent to Mast server.
 *
 * Class provides basic properties for sending request to MAST server as well as main functionality for sending
 * given request by our web application (implementation of abstract methods from abstract class RequestObject).
 *
 * @author Ľuboslav Halama
 */
public class MastRequestObject extends RequestObject {

    @JsonIgnore
    private static final String CONNECTION_URL = "https://mast.stsci.edu/api/v0/invoke?";
    @JsonIgnore
    private static final String REQUEST_PARAMS_PREFIX = "request=";

    @JsonIgnore
    private static final int DEFAULT_PAGE = 1;
    @JsonIgnore
    private static final int DEFAULT_PAGE_SIZE = 500;

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
    private int page = DEFAULT_PAGE;
    private int pagesize = DEFAULT_PAGE_SIZE;

    private MastRequestObject(MastServices service) {
        this.service = service.toString();
        params.put("format", "json");
    }

    public MastRequestObject(MastServices service, SearchFormInput input) {
        this(service);
        switch (service) {
            case MAST_NAME_LOOKUP:
                params.put("input", input.getSearchInput());
                break;
            case MAST_CAOM_CONE:
                params.putAll(new PositionInput(input.getSearchInput()).getAsMap());
                break;
            default:
                throw new IllegalArgumentException("There is no service provided by MAST for searching by: "
                        + input.getSearchBy());

        }

        // common parameters for all services
        page = input.getPage();
        pagesize = input.getPagesize();
    }

    public MastRequestObject(MastServices service, PositionInput position, SearchFormInput input) {
        this(service);
        params.putAll(position.getAsMap());

        // common parameters for all services
        page = input.getPage();
        pagesize = input.getPagesize();
    }

//    public MastRequestObject(MastServices service, double ra, double dec, double radius) {
//        this.service = service.toString();
//        params.put("ra", ra);
//        params.put("dec", dec);
//        params.put("radius", radius);
//    }

//    public String toJson() {
//        return new Gson().toJson(this);
//    }

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
        System.out.println("Params: " + REQUEST_PARAMS_PREFIX + gson.toJson(this));
        return (REQUEST_PARAMS_PREFIX + gson.toJson(this)).getBytes();
    }
}
