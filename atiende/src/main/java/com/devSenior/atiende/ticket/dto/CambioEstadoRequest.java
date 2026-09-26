package com.devsenior.atiende.ticket.dto;

import com.devsenior.atiende.ticket.EstadoTicket;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoRequest(

        @NotNull(message = "El nuevo estado es obligatorio")
        EstadoTicket estado
) {
}