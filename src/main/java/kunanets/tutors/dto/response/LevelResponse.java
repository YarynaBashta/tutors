package kunanets.tutors.dto.response;


import kunanets.tutors.entity.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LevelResponse {
    private Long id;
    private String name;

    public LevelResponse(Level level) {
        id = level.getId();
        name = level.getName();
    }

}
