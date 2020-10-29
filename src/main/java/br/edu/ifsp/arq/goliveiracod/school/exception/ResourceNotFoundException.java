package br.edu.ifsp.arq.goliveiracod.school.exception;

public class ResourceNotFoundException extends NullPointerException {
    public ResourceNotFoundException(String error) {
        super(error);
    }
}
