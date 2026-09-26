package com.devsenior.atiende.compartido;

import java.time.LocalDateTime;

public record ErrorResponse(
        int codigo,
        String error,
        String mensaje,
        LocalDateTime fecha
) {
}