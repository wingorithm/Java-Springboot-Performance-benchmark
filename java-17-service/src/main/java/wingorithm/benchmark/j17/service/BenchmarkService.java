package wingorithm.benchmark.j17.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wingorithm.benchmark.j17.infrastructure.proxy.Go3rdPartyClient;
import wingorithm.benchmark.j17.infrastructure.repository.BenchmarkRepository;
import wingorithm.benchmark.j17.model.PerformanceRecordsEntity;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BenchmarkService {
    private final BenchmarkRepository repository;
    private final Go3rdPartyClient goClient;

    @Value("${benchmark.service.id}")
    private String serviceId;

    public Map<String, Object> benchmarkWriteFlow(String transactionMessage) {
        log.info("[{}] Starting write flow. Payload message: {}", serviceId, transactionMessage);

        long currentTotal = repository.countByUserId(serviceId);
        log.info("[{}] Current total records in DB: {}", serviceId, currentTotal);

        boolean isSuccess = false;
        try {
            goClient.getMockData("200ms");
            isSuccess = true;
            log.info("[{}] 3rd party call successful.", serviceId);
        } catch (Exception e) {
            log.warn("[{}] 3rd party call failed: {}", serviceId, e.getMessage());
        }

        PerformanceRecordsEntity record = new PerformanceRecordsEntity();
        record.setUserId(serviceId);
        record.setTransactionMessage(transactionMessage);
        record.setProcessed(isSuccess);
        record.setUpdateAt(java.time.OffsetDateTime.now());
        record.setCreatedAt(java.time.OffsetDateTime.now());
        repository.save(record);

        return Map.of(
                "status", isSuccess ? "success" : "failed",
                "service", serviceId
        );
    }
}
