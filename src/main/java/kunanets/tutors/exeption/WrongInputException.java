package kunanets.tutors.exeption;

public class WrongInputException extends Exception {

    public WrongInputException() {
    }

    public WrongInputException(String message) {
        super(message);
    }
}
