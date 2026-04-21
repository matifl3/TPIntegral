package com.example.veterinariaIntegrador.service;

import com.example.veterinariaIntegrador.dao.ClienteDAO;
import com.example.veterinariaIntegrador.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteDAO clienteDAO;
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> listaCliente() {return clienteDAO.listarTodosCliente();}

    public Optional<Cliente> buscarId(Integer id) {return clienteDAO.buscarPorId(id);}

    public boolean crearCLiente(Cliente cliente) {
        int filasAfectada=clienteDAO.crearCliente(cliente);
        return filasAfectada > 0;
    }

    public boolean actualizarCLiente(Cliente cliente,int id) {
        int filasAfectada=clienteDAO.actualizarCliente(id,cliente);
        return filasAfectada > 0;
    }

    public boolean eliminarLogico(Integer idCliente) {
        int filasAfectadas = clienteDAO.eliminarLogicoCliente(idCliente);
        return filasAfectadas > 0;
    }

}
