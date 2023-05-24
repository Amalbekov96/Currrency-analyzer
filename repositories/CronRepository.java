package currency.pick.kg.repositories;

import currency.pick.kg.entities.CronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CronRepository extends JpaRepository<CronEntity, UUID> {

    List<CronEntity> getAllByIsActiveTrue();
}
