package es.upm.grise.prof.curso2025.integrador1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.util.List;


public class CuentaBancariaTest {
    private CuentaBancaria cuentaBancaria;

    @BeforeEach
    void setUp(){
        cuentaBancaria = new CuentaBancaria("1234", 10);
    }

    @Test
    void operacionNulaTest() throws Exception{
        assertThrows(OperationNulaException.class, () -> {cuentaBancaria.addOperacion(null);});
    }

    @Test
    void operacionDuplicadaTest() throws Exception{
        Operacion op1 = Mockito.mock(Operacion.class);
        Mockito.when(op1.getId()).thenReturn(10L);
        Mockito.when(op1.getImporte()).thenReturn(10.0);
        cuentaBancaria.addOperacion(op1);
        assertThrows(OperationDuplicadaException.class, () -> {cuentaBancaria.addOperacion(op1);});
    }

    @Test
    void operacionAddCorrectaTest() throws Exception{
        Operacion op1 = Mockito.mock(Operacion.class);
        Mockito.when(op1.getId()).thenReturn(10L);
        Mockito.when(op1.getImporte()).thenReturn(10.0);
        cuentaBancaria.addOperacion(op1);
        assertEquals(1, cuentaBancaria.operaciones.size());
    }

    @Test
    void salgoNegativoErrorTest() throws Exception{
        Operacion op1 = Mockito.mock(Operacion.class);
        Mockito.when(op1.getId()).thenReturn(10L);
        Mockito.when(op1.getImporte()).thenReturn(-20.0);
        cuentaBancaria.admiteDescubierto = false;
        cuentaBancaria.addOperacion(op1);
        assertThrows(SaldoNegativoException.class, () -> {cuentaBancaria.getSaldoActual();});
    }
}
