package it.unibz.cspiess.exception;

/**
 * Created by claudio on 31/01/2017.
 */
public class QuestionNotFoundException extends Exception {
    public QuestionNotFoundException(String error) {
        super(error);
    }
}
