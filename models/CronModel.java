package currency.pick.kg.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CronModel extends BaseModel {

    private String name;
    private String cron;
    private String description;
    private Boolean isActive;
}
