package com.example.veterinariaIntegrador.dao;

import com.example.veterinariaIntegrador.Enums.Estado;
import com.example.veterinariaIntegrador.model.Turno;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TurnoDAO {

    private final JdbcTemplate jdbcTemplate;

    public TurnoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Turno> turnoRowMapper = (rs, rowNum) -> {
        Turno t = new Turno();
        t.setFecha(rs.getDate("fecha").toLocalDate());
        t.setHora(rs.getTime("hora").toLocalTime());
        t.setMotivo(rs.getString("motivo"));
        t.setEstado(Estado.valueOf(rs.getString("estado")));
        t.setId_cliente(rs.getInt("id_cliente"));       // <--
        t.setId_veterinario(rs.getInt("id_veterinario")); // <--
        t.setId_mascota(rs.getInt("id_mascota"));
        return t;
    };

    public int crearTurno(Turno t) {
        String sql = """
                INSERT INTO turno (fecha, hora, motivo, estado, id_cliente, id_veterinario, id_mascota)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                t.getFecha(), t.getHora(), t.getMotivo(), t.getEstado(),
                t.getId_cliente(), t.getId_veterinario(), t.getId_mascota());
    }

    public List<Turno> listarTodosTurnos() {
        String sql = "SELECT * FROM turno";
        return jdbcTemplate.query(sql, turnoRowMapper);
    }

    public int actualizarTurno(Integer idTurno, Turno t) {
        String sql = """
                UPDATE turno
                SET fecha = ?, hora = ?, motivo = ?, estado = ?,
                    id_cliente = ?, id_veterinario = ?, id_mascota = ?
                WHERE id_turno = ?
                """;
        return jdbcTemplate.update(sql,
                t.getFecha(), t.getHora(), t.getMotivo(), t.getEstado(),
                t.getId_cliente(), t.getId_veterinario(), t.getId_mascota(),
                idTurno);
    }

    public int cancelarTurno(Integer idTurno) {
        String sql = """
                UPDATE turno
                SET estado = 'cancelado'
                WHERE id_turno = ? AND estado = 'pendiente'
                """;
        return jdbcTemplate.update(sql, idTurno);
    }

    public Optional<Turno> buscarPorId(Integer idTurno) {
        String sql = """
                SELECT id_turno, fecha, hora, motivo, estado, id_cliente, id_veterinario, id_mascota
                FROM turno
                WHERE id_turno = ?
                """;
        List<Turno> lista = jdbcTemplate.query(sql, turnoRowMapper, idTurno);
        return lista.stream().findFirst();
    }

    public List<Turno> listarPorCliente(Integer idCliente) {
        String sql = "SELECT * FROM turno WHERE id_cliente = ?";
        return jdbcTemplate.query(sql, turnoRowMapper, idCliente);
    }

    public List<Turno> listarPorVeterinario(Integer idVeterinario) {
        String sql = "SELECT * FROM turno WHERE id_veterinario = ?";
        return jdbcTemplate.query(sql, turnoRowMapper, idVeterinario);
    }
}
