package wingorithm.benchmark.j17.infrastructure.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "go-service", url = "${benchmark.go-service.url}")
public interface Go3rdPartyClient {

    @GetMapping("/api/data")
    String getMockData(@RequestParam("delay") String delay);
}
