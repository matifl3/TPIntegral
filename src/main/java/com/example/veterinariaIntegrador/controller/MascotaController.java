package com.example.veterinariaIntegrador.controller;


import com.example.veterinariaIntegrador.dao.MascotaDAO;
import com.example.veterinariaIntegrador.model.Cliente;
import com.example.veterinariaIntegrador.model.Mascota;
import com.example.veterinariaIntegrador.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
public class MascotaController {
    MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    public List<Mascota> listarMascotas(){return mascotaService.listarMascotas();}

    @RequestMapping("/{id}")
    public ResponseEntity<Mascota> findById(@PathVariable int id){
        return mascotaService.buscarId(id).map(resp -> ResponseEntity.ok().body(resp)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Mascota mascota) {
        boolean creado = mascotaService.crearMascota(mascota);

        if (creado) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Mascota creado correctamente");
        }

        return ResponseEntity.badRequest().body("No se pudo crear el Mascota");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLogico(@PathVariable Integer id) {
        boolean eliminado = mascotaService.eliminarMascota(id);

        if (eliminado) {
            return ResponseEntity.ok("mascota eliminado correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(
            @PathVariable Integer id,
            @RequestBody Mascota mascota
    ) {
        boolean actualizado = mascotaService.actualizarMascota(id,mascota);

        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado correctamente");
        }

        return ResponseEntity.notFound().build();
    }
}
