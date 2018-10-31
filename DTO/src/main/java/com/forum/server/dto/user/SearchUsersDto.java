package com.forum.server.dto.user;

/**
 * Created by root on 02.09.16.
 */
public class SearchUsersDto {
    private int count;
    private ShortUsersDto shortUsersDto;

    public int getCount() {
        return count;
    }

    public ShortUsersDto getShortUsersDto() {
        return shortUsersDto;
    }
    
    protected SearchUsersDto(){}

    private SearchUsersDto(Builder builder) {
        this.count = builder.count;
        this.shortUsersDto = builder.shortUsersDto;
    }

    public static class Builder {
        private int count;
        private ShortUsersDto shortUsersDto;

        public Builder Count(int count) {
            this.count = count;
            return this;
        }

        public Builder ShortUsersDto(ShortUsersDto shortUsersDto) {
            this.shortUsersDto = shortUsersDto;
            return this;
        }
        
        public SearchUsersDto build(){return new SearchUsersDto(this);}
    }
}
