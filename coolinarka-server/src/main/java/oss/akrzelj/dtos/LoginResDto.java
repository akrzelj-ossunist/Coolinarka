package oss.akrzelj.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class LoginResDto {
    private Boolean success;
    private String token;
    private UserResDto userResDto;
}