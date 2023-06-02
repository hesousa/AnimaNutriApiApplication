package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.Aluno;
import br.com.una.animanutri.model.service.AlunoService;
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
@RequestMapping("/Aluno")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(summary = "Cria um novo aluno", tags = { "alunos", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno){
        Aluno alunoSalvo = alunoService.salvar(aluno);

        if (alunoSalvo == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(aluno);
        }
    }

    @Operation(summary = "Altera um Professor pelo id Id Id", tags = { "alunos", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> alterar(@PathVariable Long id, @RequestBody Aluno aluno){
        Aluno alunoAlterado = alunoService.altera(id, aluno);

        if (alunoAlterado == null) {
            return ResponseEntity.noContent().build();
        }

        //earlier return
        return ResponseEntity.ok(aluno);
    }

    @Operation(
            summary = "Busca de Pacient por  Id",
            description = "Get a Cooperative object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "alunos", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscaPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.buscaPorId(id);

        if (!aluno.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(aluno.get());
        }
    }

    @Operation(summary = "Busca todos os Alunos", tags = { "alunos", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Aluno.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existe Aluno", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<Aluno>> buscaPorTodos() {
        List<Aluno> todos = alunoService.todos();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @Operation(summary = "Deleta Aluno por Id", tags = { "alunos", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePorId(@PathVariable Long id){
        alunoService.removePorId(id);

        return ResponseEntity.noContent().build();
    }
}
