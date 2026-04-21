package com.example.veterinariaIntegrador.service;

import com.example.veterinariaIntegrador.dao.TurnoDAO;
import com.example.veterinariaIntegrador.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoDAO turnoDAO;

    public TurnoService(TurnoDAO turnoDAO) {
        this.turnoDAO = turnoDAO;
    }

    public List<Turno> listarTurnos() { return turnoDAO.listarTodosTurnos(); }

    public Optional<Turno> buscarId(Integer id) { return turnoDAO.buscarPorId(id); }

    public List<Turno> listarPorCliente(Integer idCliente) { return turnoDAO.listarPorCliente(idCliente); }

    public List<Turno> listarPorVeterinario(Integer idVeterinario) { return turnoDAO.listarPorVeterinario(idVeterinario); }

    public boolean crearTurno(Turno turno) {
        int filasAfectadas = turnoDAO.crearTurno(turno);
        return filasAfectadas > 0;
    }

    public boolean actualizarTurno(Integer idTurno, Turno turno) {
        int filasAfectadas = turnoDAO.actualizarTurno(idTurno, turno);
        return filasAfectadas > 0;
    }

    public boolean cancelarTurno(Integer idTurno) {
        int filasAfectadas = turnoDAO.cancelarTurno(idTurno);
        return filasAfectadas > 0;
    }
}