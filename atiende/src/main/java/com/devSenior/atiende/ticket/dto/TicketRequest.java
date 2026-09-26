package com.devsenior.atiende.ticket.dto;

import com.devsenior.atiende.ticket.Canal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketRequest(

        @NotBlank(message = "El nombre del cliente es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String clienteNombre,

        @NotBlank(message = "El contacto del cliente es obligatorio")
        @Size(max = 120, message = "El contacto no puede superar 120 caracteres")
        String clienteContacto,

        @NotNull(message = "El canal es obligatorio")
        Canal canal,

        @NotBlank(message = "El mensaje es obligatorio")
        @Size(max = 2000, message = "El mensaje no puede superar 2000 caracteres")
        String mensaje
) {
}