package schema;

public class LoginResponse {
    private boolean success;
    private String message;
    private String token;

    public LoginResponse() {
        this(false, "", "");
    }

    public LoginResponse(String token) {
        this(true, "", token);
    }

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.token = "";
    }

    public LoginResponse(boolean success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }
    
    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    

}
