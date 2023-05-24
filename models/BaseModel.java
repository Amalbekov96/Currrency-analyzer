package currency.pick.kg.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class BaseModel {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
