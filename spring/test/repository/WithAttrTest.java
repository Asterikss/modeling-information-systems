import model.Cook;
import model.Feast;
import model.Presence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class WithAttrTest {
    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private FeastRepository feastRepository;
    @Autowired
    private PresenceRepository presenceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Cook c1;
    Feast f1;
    Presence p1;

    @BeforeEach
    public void initData() {
        c1 = Cook.builder()
                .name("Lalus")
                .age(30)
                .specialDish("Lasagne")
                .build();

        f1 = Feast.builder()
                .label("Great Feast")
                .duration(3)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(presenceRepository);
        assertNotNull(cookRepository);
        assertNotNull(feastRepository);
    }

    @Test
    public void testSave() {
        cookRepository.save(c1);
        feastRepository.save(f1);
        p1 = Presence.builder()
                .cook(c1)
                .feast(f1)
                .mainTask("Setting the great table")
                .startDate(LocalDate.now())
                .build();
        presenceRepository.save(p1);
        entityManager.flush();
        long count = presenceRepository.count();
        System.out.println(count);
        assertEquals(1, count);
    }



}
