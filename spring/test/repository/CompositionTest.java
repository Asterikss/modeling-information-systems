import model.Feast;
import model.Milestone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CompositionTest {
    @Autowired
    private FeastRepository feastRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Feast f1;
    Milestone m1;

    @BeforeEach
    public void initData() {
        f1 = Feast.builder()
                .label("Great Feast")
                .duration(3)
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(milestoneRepository);
        assertNotNull(feastRepository);
    }

    @Test
    public void testSave() {
        feastRepository.save(f1);
        m1 = Milestone.builder()
                .owner(f1)
                .expectedLength(0.5)
                .name("Inviting all guests")
                .build();
        milestoneRepository.save(m1);
        entityManager.flush();
        long count = milestoneRepository.count();
        System.out.println(count);
        assertEquals(1, count);
    }
}
