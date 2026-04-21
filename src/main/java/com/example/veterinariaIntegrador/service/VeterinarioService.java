package com.example.veterinariaIntegrador.service;

import com.example.veterinariaIntegrador.dao.VeterinarioDAO;
import com.example.veterinariaIntegrador.model.Veterianrio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioService {
    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioService(VeterinarioDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }

    public List<Veterianrio> listarVeterinarios() { return veterinarioDAO.listarTodosVeterinario(); }

    public Optional<Veterianrio> buscarId(Integer id) { return veterinarioDAO.buscarPorId(id); }

    public boolean crearVeterinario(Veterianrio veterinario) {
        int filasAfectadas = veterinarioDAO.crearVeterianrio(veterinario);
        return filasAfectadas > 0;
    }

    public boolean actualizarVeterinario(Integer idVeterinario, Veterianrio veterinario) {
        int filasAfectadas = veterinarioDAO.actualizarVeterianrio(idVeterinario, veterinario);
        return filasAfectadas > 0;
    }

    public boolean eliminarVeterinario(Integer idVeterinario) {
        int filasAfectadas = veterinarioDAO.eliminarVeterinario(idVeterinario);
        return filasAfectadas > 0;
    }
}
