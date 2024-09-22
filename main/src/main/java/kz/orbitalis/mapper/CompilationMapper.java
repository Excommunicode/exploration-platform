package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import kz.orbitalis.dto.compilation.CompilationDto;
import kz.orbitalis.dto.compilation.UpdateCompilationRequest;
import kz.orbitalis.model.Compilation;

import java.util.List;

@Mapper(componentModel = "spring", uses = EventMapper.class)
public interface CompilationMapper {

    Compilation toModel(UpdateCompilationRequest updateCompilationRequest);

    Compilation toModel(CompilationDto compilationDto);

    CompilationDto toDto(Compilation compilation);

    List<CompilationDto> toListDto(List<Compilation> compilations);

    CompilationDto updateCompilation(@MappingTarget CompilationDto compilationDto, UpdateCompilationRequest updateCompilationRequest);
}