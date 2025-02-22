import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cook_id", "feast_id"})
})
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cook_id", nullable = false)
    @NotNull
    private Cook cook;

    @ManyToOne
    @JoinColumn(name = "feast_id", nullable = false)
    @NotNull
    private Feast feast;

    @NotNull
    private LocalDate startDate;

    private String mainTask;

}
