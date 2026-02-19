package wingorithm.benchmark.j17.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "performance_records")
public class PerformanceRecordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "transaction_message")
    private String transactionMessage;

    private boolean processed;

    @Column(name = "updated_at")
    private OffsetDateTime updateAt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}