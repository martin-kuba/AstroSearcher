package org.astrosearcher.classes;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Abstract class which is used as super class for uniform and simple work
 * with all requestObjects used in this project.
 *
 * @author Ľuboslav Halama
 */
public abstract class RequestObject {

    public abstract String send();

    public abstract URL getConnectionURL() throws MalformedURLException;

    public abstract byte[] getParamsAsBytes();
}
