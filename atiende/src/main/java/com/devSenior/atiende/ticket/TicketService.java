package com.devsenior.atiende.ticket;

import com.devsenior.atiende.compartido.RecursoNoEncontradoException;
import com.devsenior.atiende.ticket.dto.TicketRequest;
import com.devsenior.atiende.ticket.dto.TicketResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Transactional
    public TicketResponse crear(TicketRequest request) {
        Ticket ticket = ticketMapper.toEntity(request);
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setPrioridad(Prioridad.MEDIA);
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }

    @Transactional(readOnly = true)
    public List<TicketResponse> listar(EstadoTicket estado) {
        List<Ticket> tickets = (estado == null)
                ? ticketRepository.findAllByOrderByFechaCreacionDesc()
                : ticketRepository.findByEstadoOrderByFechaCreacionDesc(estado);
        return tickets.stream()
                .map(ticketMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TicketResponse buscarPorId(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el ticket con id " + id));
        return ticketMapper.toResponse(ticket);
    }

    @Transactional
    public TicketResponse cambiarEstado(Long id, EstadoTicket nuevoEstado) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el ticket con id " + id));
        if (!ticket.getEstado().puedeCambiarA(nuevoEstado)) {
            throw new TransicionInvalidaException(ticket.getEstado(), nuevoEstado);
        }
        ticket.setEstado(nuevoEstado);
        return ticketMapper.toResponse(ticketRepository.saveAndFlush(ticket));
    }
}