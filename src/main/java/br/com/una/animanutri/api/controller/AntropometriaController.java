package br.com.una.animanutri.api.controller;

import br.com.una.animanutri.model.entities.Antropometria;
import br.com.una.animanutri.model.service.AntropometriaService;
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
@RequestMapping("/antropometria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AntropometriaController {

    @Autowired
    private AntropometriaService antropometriaService;

    @Operation(summary = "Cria uma nova medição antropométrica", tags = {"antropometria", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Antropometria.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PostMapping
    public ResponseEntity<Antropometria> salvar(@RequestBody Antropometria antropometria) {
        Antropometria antropometriaSalva = antropometriaService.salvar(antropometria);

        if (antropometriaSalva == null) {
            return ResponseEntity.noContent().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{data}").buildAndExpand(antropometriaSalva.getData()).toUri();
            return ResponseEntity.created(uri).body(antropometria);
        }
    }

    @Operation(summary = "Altera uma medição antropométrica pela data", tags = {"antropometria", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Antropometria.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})
    })
    @PutMapping("/{data}")
    public ResponseEntity<Antropometria> alterar(@PathVariable String data, @RequestBody Antropometria antropometria) {
        Antropometria antropometriaAlterada = antropometriaService.alterar(data, antropometria);

        if (antropometriaAlterada == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(antropometria);
    }

    @Operation(summary = "Busca uma medição antropométrica pela data", tags = {"antropometria", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Antropometria.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{data}")
    public ResponseEntity<Antropometria> buscaPorData(@PathVariable String data) {
        Optional<Antropometria> antropometria = antropometriaService.buscaPorData(data);

        if (!antropometria.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(antropometria.get());
        }
    }

    @Operation(summary = "Busca todas as medições antropométricas", tags = {"antropometria", "get", "filter"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Antropometria.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "Não existem medições antropométricas", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping
    public ResponseEntity<List<Antropometria>> buscaPorTodas() {
        List<Antropometria> todas = antropometriaService.buscaPorTodas();

        if (todas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(todas);
        }
    }

    @Operation(summary = "Remove uma medição antropométrica pela data", tags = {"antropometria", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/{data}")
    public ResponseEntity<?> removePorData(@PathVariable String data) {
        antropometriaService.removePorData(data);

        return ResponseEntity.noContent().build();
    }
}
