package es.upm.grise.prof.curso2025.integrador1;

public class SaldoNegativoException extends Exception{
    public SaldoNegativoException(){
        super("Saldo negativo: esta cuanta no admite saldo negativo");
    }
}
