package com.example.veterinariaIntegrador.controller;

import com.example.veterinariaIntegrador.model.Turno;
import com.example.veterinariaIntegrador.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {
    TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public List<Turno> turnoList() {
        return turnoService.listarTurnos();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Integer id) {
        return turnoService.buscarId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/turno/{id_cliente}")
    public ResponseEntity<Turno> buscarPorIdCliente(@PathVariable Integer id_cliente) {
        return turnoService.listarPorCliente(id_cliente).stream().map(ResponseEntity::ok).findFirst().orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/turno/{id_veterinario}")
    public ResponseEntity<Turno> buscarPorIdVeterinario(@PathVariable Integer id_veterinario) {
        return turnoService.listarPorVeterinario(id_veterinario).stream().map(ResponseEntity::ok).findFirst().orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Turno turno) {
        boolean creada = turnoService.crearTurno(turno);
        if (creada) {
            return ResponseEntity.status(HttpStatus.CREATED).body("turno creado");
        }
        return ResponseEntity.badRequest().body("no se creo el turno");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@RequestBody Turno turno, @PathVariable Integer id) {
        boolean actualizada = turnoService.actualizarTurno(id, turno);

        if (actualizada) {
            return ResponseEntity.ok("Turno actualizada correctamente");
        }

        return ResponseEntity.badRequest().body("No se pudo actualizar el turno.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLogico(@PathVariable Integer id) {
        boolean eliminada = turnoService.cancelarTurno(id);
        if (eliminada) {
            return ResponseEntity.ok("Turno eliminada correctamente");
        }
        return ResponseEntity.notFound().build();
    }
}
