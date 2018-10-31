package com.forum.server.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 15.08.16
 *
 * @author Dinar Rafikov (First Software Engineering Platform)
 * @version 1.0
 */
@Data
@Builder(builderMethodName = "baseBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class ShortUser {
    protected long userId;
    protected String nickname;
    protected Long rating;
    protected String rights;
}
