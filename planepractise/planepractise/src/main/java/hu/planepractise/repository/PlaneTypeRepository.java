package hu.planepractise.repository;

import hu.planepractise.model.PlaneType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneTypeRepository extends JpaRepository<PlaneType, Integer> {
}
