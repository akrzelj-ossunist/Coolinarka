package oss.akrzelj.exceptions;

public class ObjectDoesntExistException extends Exception {
    public ObjectDoesntExistException(String message) {
        super(message);
    }
}
