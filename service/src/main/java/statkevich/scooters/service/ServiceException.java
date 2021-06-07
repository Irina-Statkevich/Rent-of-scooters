package statkevich.scooters.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
