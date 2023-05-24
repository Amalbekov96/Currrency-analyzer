package currency.pick.kg.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "CRON")
public class CronEntity extends BaseEntity {
    private String name;
    private String cron;
    private String description;
    private Boolean isActive;
}
