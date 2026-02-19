package wingorithm.benchmark.j17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wingorithm.benchmark.j17.service.BenchmarkService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/benchmark")
public class BenchmarkController {

    private final BenchmarkService benchmarkService;

    @PostMapping("/write-flow")
    public ResponseEntity<Map<String, Object>> writeFlow(
            @RequestBody Map<String, String> payload
    ) {
        Map<String, Object> response = benchmarkService.benchmarkWriteFlow(
                payload.getOrDefault("transaction_message", "default_message")
        );
        return ResponseEntity.ok(response);
    }
}