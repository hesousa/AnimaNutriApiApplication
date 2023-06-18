package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.Alimento;
import br.com.una.animanutri.model.service.AlimentoService;
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
@RequestMapping("/Alimento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @Operation(summary = "Cria um novo alimento", tags = { "alimentos", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Alimento.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Alimento> salvar(@RequestBody Alimento alimento){
        Alimento alimentoSalvo = alimentoService.salvar(alimento);

        if (alimentoSalvo == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alimentoSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(alimento);
        }
    }

    @Operation(summary = "Altera um alimento pelo id", tags = { "alimentos", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Alimento.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Alimento> alterar(@PathVariable Long id, @RequestBody Alimento alimento){
        Alimento alimentoAlterado = alimentoService.altera(id, alimento);

        if (alimentoAlterado == null) {
            return ResponseEntity.noContent().build();
        }

        //earlier return
        return ResponseEntity.ok(alimento);
    }

    @Operation(
            summary = "Busca de Alimento por Id",
            description = "Get a Alimento object by specifying its id. The response is Alimento object with id, nome, porcao, calorias, medida caseira e unidade.",
            tags = { "alimentos", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Alimento.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Alimento> buscaPorId(@PathVariable Long id) {
        Optional<Alimento> alimento = alimentoService.buscaPorId(id);

        if (!alimento.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(alimento.get());
        }
    }

    @Operation(summary = "Busca todos os alimentos", tags = { "alimentos", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Alimento.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existe Alimento", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<Alimento>> buscaPorTodos() {
        List<Alimento> todos = alimentoService.todos();

        if (todos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todos);
        }
    }

    @Operation(summary = "Deleta Alimento por Id", tags = { "alimentos", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePorId(@PathVariable Long id){
        alimentoService.removePorId(id);

        return ResponseEntity.noContent().build();
    }
}
