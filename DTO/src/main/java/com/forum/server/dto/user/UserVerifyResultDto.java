package com.forum.server.dto.user;

import com.forum.server.dto.Data;

/**
 * Created by root on 22.09.16.
 */
public class UserVerifyResultDto implements Data {
    private String nickname;
    private String rights;

    public String getNickname() {
        return nickname;
    }

    public String getRights() {
        return rights;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    protected UserVerifyResultDto(){}

    private UserVerifyResultDto(Builder builder) {
        this.nickname = builder.nickname;
        this.rights = builder.rights;
    }

    public static class Builder {
        private String nickname;
        private String rights;

        public Builder Nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder Rights(String rights) {
            this.rights = rights;
            return this;
        }

        public UserVerifyResultDto build() {return new UserVerifyResultDto(this);}
    }
    
}
