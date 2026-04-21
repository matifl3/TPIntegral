package com.example.veterinariaIntegrador.dao;

import com.example.veterinariaIntegrador.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAO {
    private final JdbcTemplate jdbcTemplate;

    public ClienteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int crearCliente(Cliente c) {
        String sql = "INSERT INTO cliente (nombre,apellido,telefono,email,dirrecion,activo)VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, c.getNombre(), c.getApellido(), c.getTelefono(), c.getEmail(), c.getDireccion(), c.getActivo());
    }

    private final RowMapper<Cliente> clienteRowMapper = (rs, rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(rs.getInt("id_cliente"));
        cliente.setNombre((rs.getString("nombre")));
        cliente.setApellido((rs.getString("apellido")));
        cliente.setTelefono((rs.getString("telefono")));
        cliente.setEmail((rs.getString("email")));
        cliente.setDireccion((rs.getString("direccion")));
        cliente.setActivo(rs.getBoolean("activo"));
        return cliente;
    };

    public List<Cliente> listarTodosCliente() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, clienteRowMapper);
    }

    public int actualizarCliente(Integer idCliente, Cliente cliente) {
        String sql = """
                UPDATE cliente
                SET nombre = ?, apellido = ?, telefono = ?, email = ?, direccion = ?
                WHERE id_cliente = ? AND activo = TRUE
                """;

        return jdbcTemplate.update(
                sql,
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion(),
                idCliente
        );
    }

    public int eliminarLogicoCliente(Integer idCliente) {
        String sql = """
                UPDATE cliente
                SET activo = FALSE
                WHERE id_cliente = ? AND activo = TRUE
                """;

        return jdbcTemplate.update(sql, idCliente);
    }

    public Optional<Cliente> buscarPorId(Integer idCliente) {
        String sql = """
                SELECT id_cliente, nombre, apellido, telefono, email, direccion, activo
                FROM cliente
                WHERE id_cliente = ? AND activo = TRUE
                """;

        List<Cliente> clientes = jdbcTemplate.query(sql, clienteRowMapper, idCliente);

        return clientes.stream().findFirst();
    }

}
