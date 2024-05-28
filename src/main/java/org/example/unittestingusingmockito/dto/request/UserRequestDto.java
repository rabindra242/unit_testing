package org.example.unittestingusingmockito.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String address;
    @NotNull
    private Integer phoneNo;
    @NotNull
    private Integer zip;
}
