package Exceptions;

public class IllegalInputException extends Exception {
    public IllegalInputException(String errorMessage) {
        super(errorMessage);
    }
}