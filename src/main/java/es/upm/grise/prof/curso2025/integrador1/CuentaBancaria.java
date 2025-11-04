package es.upm.grise.prof.curso2025.integrador1;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.prof.curso2025.integrador1.OperacionDuplicadaException;
import es.upm.grise.prof.curso2025.integrador1.OperacionNulaException;

public class CuentaBancaria {
	
	String numeroCuenta;
	double saldoInicial;
	boolean admiteDescubierto;
	List<Operacion> operaciones;
	
	//
	// CUERPO DEL EXAMEN
	//
	
	public CuentaBancaria(String numeroCuenta, double saldoInicial) {
		this.numeroCuenta = numeroCuenta;
		this.saldoInicial = saldoInicial;
		this.operaciones = new ArrayList<>();
		// TODO: Realizar
		
	}
		
	public void addOperacion(Operacion operacion) throws OperacionDuplicadaException, OperacionNulaException{
		if(operacion != null){
			for(Operacion o: operaciones){
				if(o.getId() == operacion.getId()){
					throw new OperacionDuplicadaException();
				}
			}
		}else{
			throw new OperacionNulaException();
		}
		operaciones.add(operacion);
		// TODO: Realizar
		
	}
	
	public double getSaldoActual() throws SaldoNegativoException {
		double importeTotal = saldoInicial;
		for(Operacion o: this.operaciones){
			importeTotal += o.getImporte();
		}
		if(!admiteDescubierto && importeTotal < 0){
			throw new SaldoNegativoException();
		}
		// TODO: Realizar
		return Math.round(importeTotal * 100.0)/100.0;
	}

}
