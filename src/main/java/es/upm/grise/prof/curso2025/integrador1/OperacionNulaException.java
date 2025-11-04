package es.upm.grise.prof.curso2025.integrador1;

public class OperacionNulaException extends Exception{
    public OperacionNulaException() {
        super("Operación nula: la operación no puede ser null.");
    }
}
