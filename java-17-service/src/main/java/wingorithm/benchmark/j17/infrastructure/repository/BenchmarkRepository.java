package wingorithm.benchmark.j17.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wingorithm.benchmark.j17.model.PerformanceRecordsEntity;

import java.util.UUID;

@Repository
public interface BenchmarkRepository extends JpaRepository<PerformanceRecordsEntity, UUID> {
    long countByUserId(String userId);
}
