package com.example.veterinariaIntegrador.controller;

import com.example.veterinariaIntegrador.model.Cliente;
import com.example.veterinariaIntegrador.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> findAll(){return clienteService.listaCliente();}

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable int id){
        return clienteService.buscarId(id).map(resp -> ResponseEntity.ok().body(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Cliente cliente) {
        boolean creado = clienteService.crearCLiente(cliente);

        if (creado) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente");
        }

        return ResponseEntity.badRequest().body("No se pudo crear el cliente");
    }

    /*
     * PUT http://localhost:8080/api/clientes/1
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(
            @PathVariable Integer id,
            @RequestBody Cliente cliente
    ) {
        boolean actualizado = clienteService.actualizarCLiente(cliente,id);

        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    /*
     * DELETE http://localhost:8080/api/clientes/1
     *
     * En realidad no borra de la base.
     * Hace una baja lógica: activo = false.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLogico(@PathVariable Integer id) {
        boolean eliminado = clienteService.eliminarLogico(id);

        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }

        return ResponseEntity.notFound().build();
    }


}
