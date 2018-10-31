package com.forum.server.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.forum.server.dto.Data;


public class QueryResultDto {

    private QueryResultInfoDto meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Data data;

    public QueryResultDto() {
    }

    public QueryResultDto(QueryResultInfoDto meta, Data data) {
        this.meta = meta;
        this.data = data;
    }

    public QueryResultInfoDto getMeta() {
        return meta;
    }

    public void setMeta(QueryResultInfoDto meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
