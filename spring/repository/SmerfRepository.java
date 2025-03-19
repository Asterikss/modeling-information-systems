import model.Smerf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SmerfRepository extends CrudRepository<Smerf, Long> {
    public List<Smerf> findByName(String name);

    @Query("from Smerf where age > :minAge")
    public List<Smerf> findSmerfWithAgeGreaterThan(@Param("minAge") double minAge);
}
