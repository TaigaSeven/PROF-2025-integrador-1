package es.upm.grise.prof.curso2025.integrador1;

import com.sun.source.tree.ImportTree;
import java.util.ArrayList;
import java.util.List;

import es.upm.grise.prof.curso2025.integrador1.OperationNulaException;
import es.upm.grise.prof.curso2025.integrador1.OperationDuplicadaException;

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
	}
		
	public void addOperacion(Operacion operacion) throws OperationNulaException, OperationDuplicadaException{
		if(operacion == null){
			throw new OperationNulaException();
		}else{
			for(Operacion o: this.operaciones){
				if(operacion.getId() == o.getId()){
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
		if(!admiteDescubierto && importeTotal < 0){
			throw new SaldoNegativoException();
		}
		return Math.round(importeTotal * 100.0)/100.0;
	}

}
