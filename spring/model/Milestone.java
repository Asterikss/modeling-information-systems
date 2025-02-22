import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "feast_id", nullable = false, updatable = false)
    private Feast owner;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 25)
    private String name;

    private String description;

    @DecimalMin("0.1")
    private double expectedLength;
}
