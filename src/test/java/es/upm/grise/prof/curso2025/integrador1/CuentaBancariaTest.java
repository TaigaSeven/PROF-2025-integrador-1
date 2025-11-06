package es.upm.grise.prof.curso2025.integrador1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class CuentaBancariaTest {
    private CuentaBancaria cuenta;

    @BeforeEach
    public void setUp(){
        cuenta = new CuentaBancaria("1234", 5);
    }

    @Test
    public void nuevaCuentaNoOperacionTest(){
        assertEquals(0, cuenta.operaciones.size());
    }

    @Test
    public void operacionNulaTest(){
        assertThrows(OperationNulaException.class, () -> {cuenta.addOperacion(null);});
    }

    @Test
    public void operacionDuplicadaTest() throws OperationNulaException, OperationDuplicadaException{
        Operacion op = Mockito.mock(Operacion.class);
        Mockito.when(op.getId()).thenReturn(10L);
        Mockito.when(op.getImporte()).thenReturn(15.0);
        cuenta.addOperacion(op);
        assertThrows(OperationDuplicadaException.class, ()->{cuenta.addOperacion(op);});
    }

    @Test
    public void operacionAddCorrectamenteTest() throws OperationNulaException,OperationDuplicadaException{
        Operacion op = Mockito.mock(Operacion.class);
        Mockito.when(op.getId()).thenReturn(10L);
        Mockito.when(op.getImporte()).thenReturn(15.0);
        Operacion op2 = Mockito.mock(Operacion.class);
        Mockito.when(op2.getId()).thenReturn(11L);
        Mockito.when(op2.getImporte()).thenReturn(15.0);
        cuenta.addOperacion(op);
        cuenta.addOperacion(op2);
        assertEquals(2, cuenta.operaciones.size());
    }

    @Test
    public void operacionNegativaNoAdmiteTest()throws OperationNulaException,OperationDuplicadaException{
        Operacion op = Mockito.mock(Operacion.class);
        Mockito.when(op.getId()).thenReturn(10L);
        Mockito.when(op.getImporte()).thenReturn(-20.0);
        cuenta.admiteDescubierto = false;
        cuenta.addOperacion(op);
        assertThrows(SaldoNegativoException.class, ()->{cuenta.getSaldoActual();});
    }

    @Test
    public void OperacionNegativaAdmiteTest()throws SaldoNegativoException, OperationDuplicadaException,OperationNulaException{
        Operacion op = Mockito.mock(Operacion.class);
        Mockito.when(op.getId()).thenReturn(10L);
        Mockito.when(op.getImporte()).thenReturn(-20.0);
        cuenta.admiteDescubierto = true;
        cuenta.addOperacion(op);
        assertEquals(-15.0, cuenta.getSaldoActual());
    }
}
