package schema;

public class ResponseSchema {

    private boolean success;
    private String message;

    public ResponseSchema(boolean success) {
        this.success = success;
        this.message = "OK";
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

    public static ResponseSchema getInstance(boolean success){
        return new ResponseSchema(success);
    }
    
    public static ResponseSchema getInstance(boolean success, String message){
        ResponseSchema res =  new ResponseSchema(success);
        res.setMessage(message);
        return res;
    }
}
