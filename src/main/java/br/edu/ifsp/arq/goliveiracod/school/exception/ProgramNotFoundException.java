package br.edu.ifsp.arq.goliveiracod.school.exception;

public class ProgramNotFoundException extends NullPointerException {
    public ProgramNotFoundException(String error) {
        super(error);
    }
}
