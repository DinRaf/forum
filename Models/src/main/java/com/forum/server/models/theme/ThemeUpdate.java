package com.forum.server.models.theme;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 01.09.16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeUpdate {
    private String title;
    private String sectionUrl;
}
