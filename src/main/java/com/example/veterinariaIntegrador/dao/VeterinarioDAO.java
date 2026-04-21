package com.example.veterinariaIntegrador.dao;

import com.example.veterinariaIntegrador.model.Veterianrio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VeterinarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public VeterinarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Veterianrio> veterinarioRowMapper = (rs, rowNum) -> {
        Veterianrio v = new Veterianrio();
        v.setNombre(rs.getString("nombre"));
        v.setApellido(rs.getString("apellido"));
        v.setMatricula(rs.getString("matricula"));
        v.setEspecialidad(rs.getString("especialidad"));
        v.setTelefono(rs.getString("telefono"));
        v.setEmail(rs.getString("email"));
        return v;
    };

    public int crearVeterianrio(Veterianrio v) {
        String sql = """
                INSERT INTO veterinario (nombre, apellido, matricula, especialidad, telefono, email)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                v.getNombre(), v.getApellido(), v.getMatricula(),
                v.getEspecialidad(), v.getTelefono(), v.getEmail());
    }

    public List<Veterianrio> listarTodosVeterinario() {
        String sql = "SELECT * FROM veterinario";
        return jdbcTemplate.query(sql, veterinarioRowMapper);
    }

    public int actualizarVeterianrio(Integer idVeterianrio, Veterianrio v) {
        String sql = """
                UPDATE veterinario
                SET nombre = ?, apellido = ?, matricula = ?, especialidad = ?, telefono = ?, email = ?
                WHERE id_veterinario = ?
                """;
        return jdbcTemplate.update(sql,
                v.getNombre(), v.getApellido(), v.getMatricula(),
                v.getEspecialidad(), v.getTelefono(), v.getEmail(),
                idVeterianrio);
    }

    public int eliminarVeterinario(Integer idVeterinario) {
        String sql = "DELETE FROM veterinario WHERE id_veterinario = ?";
        return jdbcTemplate.update(sql, idVeterinario);
    }

    public Optional<Veterianrio> buscarPorId(Integer idVeterinario) {
        String sql = """
                SELECT id_veterinario, nombre, apellido, matricula, especialidad, telefono, email
                FROM veterinario
                WHERE id_veterinario = ?
                """;
        List<Veterianrio> lista = jdbcTemplate.query(sql, veterinarioRowMapper, idVeterinario);
        return lista.stream().findFirst();
    }
}