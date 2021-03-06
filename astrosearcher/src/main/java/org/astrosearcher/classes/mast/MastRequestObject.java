package org.astrosearcher.classes.mast;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class MastRequestObject {

    // TODO: finish implementation for the rest of object attributes
    private String service;
    private Map<String, Object> params = new HashMap<>();
    //private String format = "json";
    //private Object data;
    //private String fileName;
    //private int timeout = 30;
    //private boolean clearCache = false;
    //private boolean removeCache = false;
    //private boolean removeNullColumns = true;
    //private String page;
    //private int pageSize = 100;

    public MastRequestObject(Services service, String name) {
        this.service = service.toString();
        params.put("input", name);
        params.put("format", "json");
    }

    public MastRequestObject(Services service, double ra, double dec) {
        this.service = service.toString();
        params.put("ra", ra);
        params.put("dec", dec);
    }

    public MastRequestObject(Services service, double ra, double dec, double radius) {
        this.service = service.toString();
        params.put("ra", ra);
        params.put("dec", dec);
        params.put("radius", radius);
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
