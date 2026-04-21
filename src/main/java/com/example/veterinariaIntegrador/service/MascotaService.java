package com.example.veterinariaIntegrador.service;

import com.example.veterinariaIntegrador.dao.MascotaDAO;
import com.example.veterinariaIntegrador.model.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    private final MascotaDAO mascotaDAO;

    public MascotaService(MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }

    public List<Mascota> listarMascotas() { return mascotaDAO.listarMascotas(); }

    public Optional<Mascota> buscarId(Integer id) { return mascotaDAO.buscaPorId(id); }

    public boolean crearMascota(Mascota mascota) {
        int filasAfectadas = mascotaDAO.crearMascota(mascota);
        return filasAfectadas > 0;
    }

    public boolean actualizarMascota(Integer idMascota, Mascota mascota) {
        int filasAfectadas = mascotaDAO.actualizarMascotas(idMascota, mascota);
        return filasAfectadas > 0;
    }

    public boolean eliminarMascota(Integer idMascota) {
        int filasAfectadas = mascotaDAO.eliminarLogicoMascota(idMascota);
        return filasAfectadas > 0;
    }
}
