package hu.locomotiveexam2.repository;

import hu.locomotiveexam2.model.Locomotive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocomotiveRepository extends JpaRepository<Locomotive, Integer> {
}
