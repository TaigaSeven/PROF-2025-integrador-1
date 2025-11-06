package es.upm.grise.prof.curso2025.integrador1;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.prof.curso2025.integrador1.OperationNulaException;
import es.upm.grise.prof.curso2025.integrador1.OperationDuplicadaException;
import es.upm.grise.prof.curso2025.integrador1.SaldoNegativoException;

public class CuentaBancaria {
	
	String numeroCuenta;
	double saldoInicial;
	boolean admiteDescubierto;
	List<Operacion> operaciones;
	
	//
	// CUERPO DEL EXAMEN
	//
	
	public CuentaBancaria(String numeroCuenta, double saldoInicial)  {
		this.numeroCuenta = numeroCuenta;
		this.saldoInicial = saldoInicial;
		operaciones = new ArrayList<>();
	}
		
	public void addOperacion(Operacion operacion)throws OperationDuplicadaException,OperationNulaException {
		if(operacion == null){
			throw new OperationNulaException();
		}else{
			for(Operacion o:this.operaciones){
				if(o.getId() == operacion.getId()){
					throw new OperationDuplicadaException();
				}
			}
		}
		this.operaciones.add(operacion);
	}
	
	public double getSaldoActual() throws SaldoNegativoException{
		double importeTotal = this.saldoInicial;
		for(Operacion o: this.operaciones){
			importeTotal += o.getImporte();
		}
		if(!this.admiteDescubierto && importeTotal<0){
			throw new SaldoNegativoException();
		}
		return Math.round(importeTotal * 100.0)/100.0;
	}

}
