package currency.pick.kg.repositories;

import currency.pick.kg.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
}
