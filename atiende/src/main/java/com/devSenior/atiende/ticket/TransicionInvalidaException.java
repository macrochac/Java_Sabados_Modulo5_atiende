package com.devsenior.atiende.ticket;

public class TransicionInvalidaException extends RuntimeException {

    public TransicionInvalidaException(EstadoTicket actual, EstadoTicket destino) {
        super("No se puede pasar un ticket de " + actual + " a " + destino);
    }
}