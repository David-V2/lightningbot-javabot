package com.pictime.codinggame.basicai.client.dto;

public class AbstractResponse {
    protected boolean success;
    protected Long wait;

    protected String description;
    protected int error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getWait() {
        return wait;
    }

    public void setWait(Long wait) {
        this.wait = wait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
