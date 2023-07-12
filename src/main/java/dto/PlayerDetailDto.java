package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@AllArgsConstructor @Builder
public class PlayerDetailDto {
    private Integer playerId;
    private String playerName;
    private String playerPosition;
    private Timestamp playerCreatedAt;

}
