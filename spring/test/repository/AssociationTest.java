import model.Cook;
import model.Hut;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AssociationTest {
    @Autowired
    private HutRepository hutRepository;
    @Autowired
    private CookRepository cookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Hut h1;
    Cook c1;

    @Test
    public void testRequiredDependencies() {
        assertNotNull(hutRepository);
        assertNotNull(cookRepository);
    }

    @BeforeEach
    public void initData() {
        h1 = Hut.builder()
                .numberOfWindows(2)
                .houseName("LalusHut")
                .build();

        c1 = Cook.builder()
                .name("Lalus")
                .age(30)
                .specialDish("Lasagne")
                .build();
    }

    @Test
    public void testSave() {
        h1.getResidents().add(c1);
        hutRepository.save(h1);
        c1.setLivesIn(h1);
        cookRepository.save(c1);
        entityManager.flush();

        Optional<Hut> byId = hutRepository.findById(c1.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getResidents());
        assertEquals(1, byId.get().getResidents().size());
    }
}
