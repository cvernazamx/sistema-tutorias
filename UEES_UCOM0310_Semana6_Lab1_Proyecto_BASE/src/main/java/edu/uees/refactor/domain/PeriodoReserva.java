package edu.uees.refactor.domain;

import java.time.LocalDateTime;

public record PeriodoReserva(LocalDateTime inicio, LocalDateTime fin) {

    public boolean esValido() {
        if (inicio == null || fin == null) {
            return false;
        }
        return fin.isAfter(inicio);
    }
}
