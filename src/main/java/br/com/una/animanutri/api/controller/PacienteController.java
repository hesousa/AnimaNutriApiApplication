package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.Aluno;
import br.com.una.animanutri.model.entities.Paciente;
import br.com.una.animanutri.model.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Cria um novo paciente", tags = { "pacientes", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Paciente.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
        Paciente pacienteSalvo = pacienteService.salvar(paciente);

        if (pacienteSalvo == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pacienteSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(paciente);
        }
    }

    @Operation(summary = "Altera um Paciente pelo id Id Id", tags = { "pacientes", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> alterar(@PathVariable Long id, @RequestBody Paciente paciente){
        Paciente pacienteAlterado = pacienteService.altera(id, paciente);

        if (pacienteAlterado == null) {
            return ResponseEntity.noContent().build();
        }

        //earlier return
        return ResponseEntity.ok(paciente);
    }

    @Operation(
            summary = "Busca de Pacient por Id",
            description = "Get a Cooperative object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "pacientes", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Paciente.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscaPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.buscaPorId(id);

        if (!paciente.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(paciente.get());
        }
    }

    @Operation(summary = "Busca todos os Paciente", tags = { "pacientes", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Paciente.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existe Paciente", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<Paciente>> buscaPorTodos() {
        List<Paciente> todos = pacienteService.todos();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @Operation(summary = "Deleta Paciente por Id", tags = { "pacientes", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePorId(@PathVariable Long id){
        pacienteService.removePorId(id);

        return ResponseEntity.noContent().build();
    }
}


//
