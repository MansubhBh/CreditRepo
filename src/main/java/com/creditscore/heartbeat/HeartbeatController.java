package com.creditscore.heartbeat;

import com.creditscore.entity.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {
    private static final long START_TIME = System.currentTimeMillis();

    private static final long FIVE_MIN = 5 * 60 * 1000;
    private static final long THREE_MIN = 3 * 60 * 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatController.class);

    public HeartbeatController() {
        LOGGER.info("creditscore started at {}", START_TIME);
    }

    @GetMapping("/liveness")
    public ResponseEntity<ServiceStatus> liveness() {
        return new ResponseEntity<>(new ServiceStatus("live"), HttpStatus.OK);
    }

    @GetMapping("/flaky-liveness")
    public ResponseEntity<ServiceStatus> flakyLiveness() {
        long now = System.currentTimeMillis();
        if (now - START_TIME >= FIVE_MIN) {
            return new ResponseEntity<>(new ServiceStatus("not alive"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ServiceStatus("live"), HttpStatus.OK);
    }

    @GetMapping("/readiness")
    public ResponseEntity<ServiceStatus> readiness() {
        return new ResponseEntity<>(new ServiceStatus("ready"), HttpStatus.OK);
    }

    @GetMapping("/flaky-readiness")
    public ResponseEntity<ServiceStatus> flakyReadiness() {
        long now = System.currentTimeMillis();
        if (now - START_TIME >= THREE_MIN) {
            return new ResponseEntity<>(new ServiceStatus("not ready"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ServiceStatus("ready"), HttpStatus.OK);
    }
}
