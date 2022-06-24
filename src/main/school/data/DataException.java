package main.school.data;

public class DataException extends Exception {
    public DataException(String message, Throwable cause) { //genera un'eccezione che ha il messaggio e la causa vera
        super(message, cause);

    }
}
