package kz.orbitalis.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import kz.orbitalis.util.NotOnlySpaces;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CategoryDto {
    private Long id;
    @NotOnlySpaces
    @NotNull(message = "Name cannot be null")
    @Length(max = 50)
    private String name;
}