package com.forum.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by root on 02.09.16.
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchUsersDto {
    private int count;
    private ShortUsersDto shortUsersDto;
}
