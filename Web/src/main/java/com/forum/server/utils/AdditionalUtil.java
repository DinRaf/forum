package com.forum.server.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 01.03.2016.
 *
 * @author Flyur Karimov (first software ingeneering platform))
 * @version 1.0
 */
public class AdditionalUtil {
    public static HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
