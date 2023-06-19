package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.PlanoAlimentar;
import br.com.una.animanutri.model.service.PlanoAlimentarService;
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
@RequestMapping("/plano-alimentar")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService planoAlimentarService;

    @Operation(summary = "Cria um novo plano alimentar", tags = { "planos-alimentares", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PlanoAlimentar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PlanoAlimentar> salvar(@RequestBody PlanoAlimentar planoAlimentar){
        PlanoAlimentar planoAlimentarSalvo = planoAlimentarService.salvar(planoAlimentar);

        if (planoAlimentarSalvo == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planoAlimentarSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(planoAlimentar);
        }
    }

    @Operation(summary = "Altera um Plano Alimentar pelo ID", tags = { "planos-alimentares", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PlanoAlimentar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> alterar(@PathVariable Long id, @RequestBody PlanoAlimentar planoAlimentar){
        PlanoAlimentar planoAlimentarAlterado = planoAlimentarService.alterar(id, planoAlimentar);

        if (planoAlimentarAlterado == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(planoAlimentar);
    }

    @Operation(
            summary = "Busca de Plano Alimentar pelo ID",
            description = "Obtém um objeto Plano Alimentar com base em seu ID.",
            tags = { "planos-alimentares", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PlanoAlimentar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> buscaPorId(@PathVariable Long id) {
        Optional<PlanoAlimentar> planoAlimentar = planoAlimentarService.buscaPorId(id);

        if (!planoAlimentar.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(planoAlimentar.get());
        }
    }

    @Operation(summary = "Busca todos os planos alimentares", tags = { "planos-alimentares", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PlanoAlimentar.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existe Plano Alimentar", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<PlanoAlimentar>> buscaPorTodos() {
        List<PlanoAlimentar> todos = planoAlimentarService.buscaPorTodos();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @Operation(summary = "Deleta um Plano Alimentar pelo ID", tags = { "planos-alimentares", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePorId(@PathVariable Long id){
        planoAlimentarService.removePorId(id);

        return ResponseEntity.noContent().build();
    }
}
