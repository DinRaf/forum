package com.forum.server.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 16/10/16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private String name;
}
