package com.example.veterinariaIntegrador.dao;

import com.example.veterinariaIntegrador.model.Cliente;
import com.example.veterinariaIntegrador.model.Mascota;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class MascotaDAO {
    JdbcTemplate jdbcTemplate;
    public MascotaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int crearMascota(Mascota c) {
        String sql = "INSERT INTO mascota (nombre,especie,raza,edad,peso,id_cliente)VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, c.getNombre(), c.getEspecie(), c.getRaza(), c.getEdad(), c.getPeso(), c.getId_cliente());
    }

    private final RowMapper<Mascota> mascotaRowMapper = (rs,rowMapper) -> {
        Mascota m = new Mascota();
        m.setNombre(rs.getString("nombre"));
        m.setEspecie(rs.getString("especie"));
        m.setRaza(rs.getString("raza"));
        m.setEdad(rs.getInt("edad"));
        m.setPeso(rs.getInt("peso"));
        m.setId_cliente(rs.getInt("id_cliente"));
        return m;
    };

    public List<Mascota> listarMascotas() {
        String sql = "SELECT * FROM mascota";
        return jdbcTemplate.query(sql, mascotaRowMapper);
    }

    public Optional<Mascota> buscaPorId(Integer id) {
        String sql = "SELECT * FROM mascota WHERE id_cliente = ?";
        List<Mascota> mascotas= jdbcTemplate.query(sql, mascotaRowMapper, id);
        return mascotas.stream().findFirst();
    }

    public int actualizarMascotas(Integer idMascota, Mascota mascota) {
        String sql = """
                UPDATE mascota
                SET nombre = ?, especie = ?, raza = ?, edad = ?, peso = ?, id_cliente = ?
                WHERE id_mascota = ?
                """;

        return jdbcTemplate.update(
                sql,
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getEdad(),
                mascota.getPeso(),
                mascota.getId_cliente(),
                idMascota
        );
    }

    public int eliminarLogicoMascota(Integer idMascota) {
        String sql = """
                DELETE 
                FROM mascotas               
                WHERE id_mascota = ?
                """;

        return jdbcTemplate.update(sql, idMascota);
    }

}
