package com.devsenior.atiende.ticket;

import com.devsenior.atiende.ticket.dto.TicketRequest;
import com.devsenior.atiende.ticket.dto.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setClienteNombre(request.clienteNombre());
        ticket.setClienteContacto(request.clienteContacto());
        ticket.setCanal(request.canal());
        ticket.setMensaje(request.mensaje());
        return ticket;
    }

    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getClienteNombre(),
                ticket.getClienteContacto(),
                ticket.getCanal(),
                ticket.getMensaje(),
                ticket.getEstado(),
                ticket.getPrioridad(),
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion()
        );
    }
}