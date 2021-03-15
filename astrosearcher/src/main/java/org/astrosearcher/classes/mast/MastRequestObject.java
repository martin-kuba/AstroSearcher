package org.astrosearcher.classes.mast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.ExceptionMSG;
import org.astrosearcher.classes.constants.MASTConstants;
import org.astrosearcher.enums.mast.MastServices;
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
    private static final Gson gson = new Gson();

    // TODO: finish implementation for the rest of object attributes
    private String service;
    private Map<String, Object> params = new HashMap<>();
    private final String format = MASTConstants.DEFAULT_FORMAT;
    //private Object data;
    //private String fileName;
    //private int timeout = 30;
    //private boolean clearCache = false;
    //private boolean removeCache = false;
    //private boolean removeNullColumns = true;
    private int page = MASTConstants.DEFAULT_PAGE;
    private int pagesize = MASTConstants.DEFAULT_PAGE_SIZE;

    private MastRequestObject(MastServices service) {
        this.service = service.toString();
        params.put("format", MASTConstants.DEFAULT_FORMAT);
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
                throw new IllegalArgumentException(ExceptionMSG.NO_SERVICE_PROVIDED_BY_MAST_EXCEPTION
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

    @Override
    public String send() {
        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(MASTConstants.CONNECTION_URL);
    }

    @Override
    public byte[] getParamsAsBytes() {
        System.out.println("Params: " + MASTConstants.REQUEST_PARAMS_PREFIX + gson.toJson(this));
        return (MASTConstants.REQUEST_PARAMS_PREFIX + gson.toJson(this)).getBytes();
    }
}
