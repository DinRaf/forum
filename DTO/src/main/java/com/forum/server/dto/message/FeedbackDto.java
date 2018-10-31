package com.forum.server.dto.message;

import com.forum.server.dto.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by root on 10.09.16.
 */
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto implements Data {
    String feedback;
}
