package oss.akrzelj.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPageDto {
    private int pageNum;
    private int pageSize;
    private int lastPage;
    private List<UserResDto> userPage;
}
