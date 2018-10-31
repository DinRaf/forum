package com.forum.server.dto.theme;

import com.forum.server.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 07.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeSearchResultDto implements Data {
    private ThemesSearchDto themesSearchDto;
    private int count;
    private String section;
}
