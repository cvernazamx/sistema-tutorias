package edu.uees.testing.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceTest {

    // En este lab los métodos no tocan dependencias externas, null es válido aquí.
    private final ReservaService servicio = new ReservaService(null, null, null);

    // --- Pruebas de puedeCancelar ---

    @Test
    @DisplayName("CP-01: Cinco horas de anticipación deben permitir cancelar")
    void cincoHorasPermitenCancelar() {
        // Arrange
        int horas = 5;

        // Act
        boolean resultado = servicio.puedeCancelar(horas);

        // Assert
        assertTrue(resultado);
    }

    @Test
    @DisplayName("CP-02: Dos horas es exactamente el límite permitido")
    void dosHorasEsElLimitePermitido() {
        // Arrange & Act & Assert
        assertTrue(servicio.puedeCancelar(2));
    }

    @Test
    @DisplayName("CP-03: Una hora está debajo del límite y no debe permitir cancelar")
    void unaHoraNoPermiteCancelar() {
        // Arrange & Act & Assert
        assertFalse(servicio.puedeCancelar(1));
    }

    @Test
    @DisplayName("CP-04: Cero horas de anticipación no debe permitir cancelar")
    void ceroHorasNoPermiteCancelar() {
        // Arrange & Act & Assert
        assertFalse(servicio.puedeCancelar(0));
    }

    // --- Pruebas de calcularTotal ---

    @Test
    @DisplayName("CP-05: Cliente NORMAL no recibe descuento")
    void normalNoRecibeDescuento() {
        // Arrange
        String tipo = "NORMAL";
        double base = 100.0;

        // Act
        double total = servicio.calcularTotal(tipo, base);

        // Assert
        assertEquals(100.0, total, 0.001);
    }

    @Test
    @DisplayName("CP-06: Cliente VIP recibe quince por ciento de descuento")
    void vipRecibeQuincePorCiento() {
        // Arrange & Act & Assert
        assertEquals(85.0, servicio.calcularTotal("VIP", 100.0), 0.001);
    }

    @Test
    @DisplayName("CP-07: Cliente ESTUDIANTE recibe diez por ciento de descuento")
    void estudianteRecibeDiezPorCiento() {
        // Arrange & Act & Assert
        assertEquals(90.0, servicio.calcularTotal("ESTUDIANTE", 100.0), 0.001);
    }

    @Test
    @DisplayName("CP-08: Monto base en cero debe retornar cero")
    void totalBaseCeroRetornaCero() {
        // Arrange & Act & Assert
        assertEquals(0.0, servicio.calcularTotal("VIP", 0.0), 0.001);
    }

    @Test
    @DisplayName("CP-09: Total negativo debe lanzar IllegalArgumentException")
    void totalNegativoEsInvalido() {
        // Arrange
        String tipo = "NORMAL";
        double baseInvalida = -1.0;

        // Act & Assert
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> servicio.calcularTotal(tipo, baseInvalida)
        );

        assertEquals("Total base inválido", ex.getMessage());
    }
}