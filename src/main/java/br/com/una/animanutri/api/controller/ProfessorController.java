package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.Aluno;
import br.com.una.animanutri.model.entities.Professor;
import br.com.una.animanutri.model.service.ProfessorService;
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
@RequestMapping("/Professor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Operation(summary = "Cria um novo Professor", tags = { "professores", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Professor.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody Professor Professor){
        Professor professorSalvo = professorService.salvar(Professor);

        if (professorSalvo == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professorSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(Professor);
        }
    }

    @Operation(summary = "Altera um Professor pelo Id", tags = { "professores", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Professor> alterar(@PathVariable Long id, @RequestBody Professor Professor){
        Professor professorAlterado = professorService.altera(id, Professor);

        if (professorAlterado == null) {
            return ResponseEntity.noContent().build();
        }

        //earlier return
        return ResponseEntity.ok(Professor);
    }

    @Operation(
            summary = "Busca de Pacient por Id",
            description = "Get a Cooperative object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "professores", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Professor.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscaPorId(@PathVariable Long id) {
        Optional<Professor> professor = professorService.buscaPorId(id);

        if (!professor.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(professor.get());
        }
    }

    @Operation(summary = "Busca todos os Professor", tags = { "professores", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Professor.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existe Professor", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<Professor>> buscaPorTodos() {
        List<Professor> todos = professorService.todos();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @Operation(summary = "Deleta Professor por Id", tags = { "professores", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePorId(@PathVariable Long id){
        professorService.removePorId(id);

        return ResponseEntity.noContent().build();
    }
}
