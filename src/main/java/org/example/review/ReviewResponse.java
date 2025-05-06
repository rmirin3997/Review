package org.example.review;

public class ReviewResponse {
    private boolean success;
    private String message;
    private String redirect;

    public ReviewResponse() {
        this.success = true; // Default to true for successful responses
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
