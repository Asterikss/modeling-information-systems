import model.Builder;
import model.Cook;
import model.Smerf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SmerfRepositoryTest {

    @Autowired
    private SmerfRepository smerfRepository;
    @Autowired
    private CookRepository cookRepository;
    @Autowired
    private BuilderRepository builderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Cook c1;
    Builder b1;

    @BeforeEach
    public void initData() {
        c1 = Cook.builder()
                .name("Lalus")
                .age(30)
                .specialDish("Lasagne")
                .build();

        b1 = Builder.builder()
                .name("Wazniak")
                .age(100)
                .tool("pickaxe")
                .build();
    }

    @Test
    public void testRequiredDependencies() {
        assertNotNull(smerfRepository);
        assertNotNull(cookRepository);
        assertNotNull(builderRepository);
    }

    @Test
    public void testFetchSmerfs() {
        Iterable<Smerf> all = smerfRepository.findAll();
        for(Smerf s : all) {
            System.out.println(s);
        }
    }

    @Test
    public void testSaveSmerf() {
        smerfRepository.save(c1);
        entityManager.flush();
        long count = smerfRepository.count();
        System.out.println(count);
        assertEquals(1, count);
    }

    @Test
    public void testSaveInvalidSmerfAge() {
        assertThrows(ConstraintViolationException.class, () -> {
            b1.setAge(-10);
            smerfRepository.save(b1);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByName() {
        smerfRepository.save(c1);
        entityManager.flush();
        List<Smerf> smerfs = smerfRepository.findByName("Lalus");
        System.out.println(smerfs);
        assertEquals(1, smerfs.size());
    }

    @Test
    public void testFindWithAgeGreaterThan() {
        smerfRepository.save(c1);
        entityManager.flush();
        List<Smerf> smerfs = smerfRepository.findSmerfWithAgeGreaterThan(20);
        System.out.println(smerfs);
        assertEquals(1, smerfs.size());
    }

}
