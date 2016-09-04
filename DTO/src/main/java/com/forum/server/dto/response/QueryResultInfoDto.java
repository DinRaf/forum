package com.forum.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;


public class QueryResultInfoDto {

    private String code = null;
    private String status = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String section = null;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subsection = null;

    public QueryResultInfoDto(String code, String status) {
        this.code = code;
        this.status = status;
    }

    public QueryResultInfoDto(String code, String status, Integer count) {
        this.code = code;
        this.status = status;
        this.count = count;
    }

    public QueryResultInfoDto(String code, String status, String section) {
        this.code = code;
        this.status = status;
        this.section = section;
    }

    public QueryResultInfoDto(String code, String status, Integer count, String subsection) {
        this.code = code;
        this.status = status;
        this.count = count;
        this.subsection = subsection;
    }

    /**
     * HTTP-код
     **/
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Статус выполнения запроса - success/fail
     **/
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * количество запрашиваемого результата запроса
     * */

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryResultInfoDto responseInfo = (QueryResultInfoDto) o;
        return Objects.equals(code, responseInfo.code) &&
                Objects.equals(status, responseInfo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, status);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseInfo {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  status: ").append(status).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
