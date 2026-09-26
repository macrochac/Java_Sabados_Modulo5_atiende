package com.devsenior.atiende.ticket;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByOrderByFechaCreacionDesc();

    List<Ticket> findByEstadoOrderByFechaCreacionDesc(EstadoTicket estado);
}