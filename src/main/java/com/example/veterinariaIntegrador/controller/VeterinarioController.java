package com.example.veterinariaIntegrador.controller;

import com.example.veterinariaIntegrador.model.Turno;
import com.example.veterinariaIntegrador.model.Veterianrio;
import com.example.veterinariaIntegrador.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinario")
public class VeterinarioController {
    VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public List<Veterianrio> findAll() {
        return veterinarioService.listarVeterinarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterianrio> buscarPorId(@PathVariable Integer id) {
        return veterinarioService.buscarId(id)
                .map(mascota -> ResponseEntity.ok(mascota))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Veterianrio turno) {
        boolean creada = veterinarioService.crearVeterinario(turno);
        if (creada) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Veterinario creado");
        }
        return ResponseEntity.badRequest().body("no se creo el Veterinario");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@RequestBody Veterianrio turno, @PathVariable Integer id) {
        boolean actualizada = veterinarioService.actualizarVeterinario(id, turno);

        if (actualizada) {
            return ResponseEntity.ok("Veterianario actualizada correctamente");
        }

        return ResponseEntity.badRequest().body("No se pudo actualizar el turno.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLogico(@PathVariable Integer id) {
        boolean eliminada = veterinarioService.eliminarVeterinario(id);
        if (eliminada) {
            return ResponseEntity.ok("Veterianrio eliminada correctamente");
        }
        return ResponseEntity.notFound().build();
    }

}
