package com.devsenior.atiende.ticket;

import com.devsenior.atiende.ticket.dto.CambioEstadoRequest;
import com.devsenior.atiende.ticket.dto.TicketRequest;
import com.devsenior.atiende.ticket.dto.TicketResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> crear(@Valid @RequestBody TicketRequest request) {
        TicketResponse creado = ticketService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public List<TicketResponse> listar(@RequestParam(required = false) EstadoTicket estado) {
        return ticketService.listar(estado);
    }

    @GetMapping("/{id}")
    public TicketResponse buscarPorId(@PathVariable Long id) {
        return ticketService.buscarPorId(id);
    }

    @PatchMapping("/{id}/estado")
    public TicketResponse cambiarEstado(@PathVariable Long id,
                                        @Valid @RequestBody CambioEstadoRequest request) {
        return ticketService.cambiarEstado(id, request.estado());
    }
}