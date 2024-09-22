package kz.orbitalis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import kz.orbitalis.dto.compilation.CompilationDto;
import kz.orbitalis.dto.compilation.UpdateCompilationRequest;
import kz.orbitalis.service.api.CompilationAdminService;

import static kz.orbitalis.util.Marker.OnCreate;
import static kz.orbitalis.util.Marker.OnUpdate;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/compilations")
public class CompilationAdminController {
    private final CompilationAdminService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto addCompilation(@Validated(OnCreate.class) @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.addCompilationDto(updateCompilationRequest);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable Long compId) {
        compilationService.deleteCompilationDto(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(@PathVariable Long compId, @Validated(OnUpdate.class) @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        return compilationService.updateCompilation(compId, updateCompilationRequest);
    }
}
