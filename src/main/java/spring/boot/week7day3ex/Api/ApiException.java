package spring.boot.week7day3ex.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
