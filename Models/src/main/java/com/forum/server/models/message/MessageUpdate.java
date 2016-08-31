package com.forum.server.models.message;

/**
 * Created by root on 31.08.16.
 */
public class MessageUpdate {
    private long update;
    private long updaterId;
    private String updaterNickName;

    public void setUpdate(long update) {
        this.update = update;
    }

    public void setUpdaterId(long updaterId) {
        this.updaterId = updaterId;
    }

    public void setUpdaterNickName(String updaterNickName) {
        this.updaterNickName = updaterNickName;
    }

    public long getUpdate() {
        return update;
    }

    public long getUpdaterId() {
        return updaterId;
    }

    public String getUpdaterNickName() {
        return updaterNickName;
    }

    private MessageUpdate(Builder builder){
        this.update = builder.update;
        this.updaterId = builder.updaterId;
        this.updaterNickName = builder.updaterNickName;
    }

    public static class Builder {
        private long update;
        private long updaterId;
        private String updaterNickName;

        public Builder Update(long update) {
            this.update = update;
            return this;

        }

        public Builder UpdaterId(long updaterId) {
            this.updaterId = updaterId;
            return this;
        }

        public Builder UpdaterNickName(String updaterNickName) {
            this.updaterNickName = updaterNickName;
            return this;
        }
    }
}
