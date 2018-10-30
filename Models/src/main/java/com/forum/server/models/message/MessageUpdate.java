package com.forum.server.models.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 31.08.16.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageUpdate {
    private long update;
    private long updaterId;
    private String updaterNickName;
}
