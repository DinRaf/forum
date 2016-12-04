package com.forum.server.utils;

import com.forum.server.dto.Data;
import com.forum.server.dto.auth.LoginDto;
import com.forum.server.dto.auth.UserNicknameDto;
import com.forum.server.dto.response.QueryResultDto;
import com.forum.server.dto.response.QueryResultInfoDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;

/**
 * 01.02.2016.
 *
 * @author Flyur Karimov (first software ingeneering platform))
 * @version 1.0
 */
public class ResponseBuilder {
    public static ResponseEntity<QueryResultDto> buildResponseGet(Data data) {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        QueryResultInfoDto responseInfo = new QueryResultInfoDto("200", "success");
        QueryResultDto queryResultDto = new QueryResultDto(responseInfo, data);
        return new ResponseEntity<>(queryResultDto, headers, HttpStatus.OK);
    }

    public static ResponseEntity<QueryResultDto> buildResponseGetWithCount(Data data, Integer count) {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        QueryResultInfoDto responseInfo = new QueryResultInfoDto("200", "success", count);
        QueryResultDto queryResultDto = new QueryResultDto(responseInfo, data);
        return new ResponseEntity<>(queryResultDto, headers, HttpStatus.OK);
    }

    public static ResponseEntity<QueryResultDto> buildResponsePostAndPut(Data data) {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        QueryResultInfoDto responseInfo = new QueryResultInfoDto("201", "success");
        QueryResultDto queryResultDto = new QueryResultDto(responseInfo, data);
        return new ResponseEntity<>(queryResultDto, headers, CREATED);
    }

    public static ResponseEntity<QueryResultDto> buildResponseLogin(LoginDto token) {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        headers.add("Auth-Token", token.getToken());
        QueryResultInfoDto responseInfo = new QueryResultInfoDto("201", "success");
        UserNicknameDto userIdDto = new UserNicknameDto.Builder().Nickname(token.getNickname()).build();
        QueryResultDto queryResultDto = new QueryResultDto(responseInfo, userIdDto);
        return new ResponseEntity<>(queryResultDto, headers, CREATED);
    }

    public static ResponseEntity<QueryResultDto> buildResponseDelete() {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        QueryResultInfoDto meta = new QueryResultInfoDto("200", "success");
        QueryResultDto queryResultDto = new QueryResultDto(meta, null);
        return new ResponseEntity<>(queryResultDto, headers, OK);
    }

    public static ResponseEntity<QueryResultDto> buildResponseRating() {
        HttpHeaders headers = AdditionalUtil.createHttpHeaders();
        QueryResultInfoDto meta = new QueryResultInfoDto("201", "success");
        QueryResultDto queryResultDto = new QueryResultDto(meta, null);
        return new ResponseEntity<>(queryResultDto, headers, OK);
    }
}
