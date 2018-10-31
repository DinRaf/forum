package com.forum.server.dto.response;

/**
 * Created by Arthur on 26.2.16.
 */
public class QueryErrorDto {
    private QueryResultInfoErrorDto meta;

    public QueryErrorDto(QueryResultInfoErrorDto meta) {
        this.meta = meta;
    }

    public QueryResultInfoErrorDto getMeta() {
        return meta;
    }

    public void setMeta(QueryResultInfoErrorDto meta) {
        this.meta = meta;
    }
}
