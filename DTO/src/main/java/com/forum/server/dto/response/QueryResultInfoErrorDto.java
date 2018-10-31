package com.forum.server.dto.response;

/**
 * Created by Arthur on 26.2.16.
 */
public class QueryResultInfoErrorDto {
    private String code = null;
    private String status = null;
    private String message = null;

    public QueryResultInfoErrorDto(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
