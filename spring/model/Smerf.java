import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Smerf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 25)
    private String name;

    @Min(0)
    private int age;

    @ManyToOne
    @JoinColumn(name = "hut_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Hut livesIn;
}
