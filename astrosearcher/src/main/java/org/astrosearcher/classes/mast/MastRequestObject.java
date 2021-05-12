package org.astrosearcher.classes.mast;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.messages.ExceptionMSG;
import org.astrosearcher.classes.constants.MASTConstants;
import org.astrosearcher.classes.mast.services.caom.crossmatch.CaomCrossmatchInput;
import org.astrosearcher.enums.mast.MastServices;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;

/**
 * Class represents request object which is used in URL request sent to Mast server.
 *
 * Class provides basic properties for sending a request to MAST server
 * as well as main functionality for sending the given request by this web
 * application (implementation of abstract methods from abstract class RequestObject).
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
    private Object data;
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
                params.put(MASTConstants.RADIUS_COLUMN, input.getRadius());
                break;
            case MAST_CAOM_CROSSMATCH:

                this.data = new CaomCrossmatchInput(input.getFile());

                params.put(MASTConstants.RA_COLUMN, ((CaomCrossmatchInput)data).getFields().get(0).getName());
                params.put(MASTConstants.DEC_COLUMN, ((CaomCrossmatchInput)data).getFields().get(1).getName());
                params.put(MASTConstants.RADIUS_COLUMN, input.getRadius());

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
        params.put(MASTConstants.RADIUS_COLUMN, input.getRadius());

        // common parameters for all services
        page = input.getPage();
        pagesize = input.getPagesize();
    }

    @Override
    public String send() {
        if ( AppConfig.DEBUG ) {
            System.out.println("\n    >>> Starting to query MAST...");
        }

        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(MASTConstants.CONNECTION_URL);
    }

    @Override
    public byte[] getParamsAsBytes() {
        if (AppConfig.DEBUG) {
            System.out.println("                params: " + MASTConstants.REQUEST_PARAMS_PREFIX + gson.toJson(this));
            System.out.println("                params (encoded): " + MASTConstants.REQUEST_PARAMS_PREFIX +
                    URLEncoder.encode(gson.toJson(this), StandardCharsets.UTF_8));
        }

        return (MASTConstants.REQUEST_PARAMS_PREFIX
                + URLEncoder.encode(gson.toJson(this), StandardCharsets.UTF_8))
                .getBytes();
    }
}
