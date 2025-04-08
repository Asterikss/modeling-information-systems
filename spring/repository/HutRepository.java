import model.Hut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HutRepository extends CrudRepository<Hut, Long> {
    @Query("from Hut as h left join fetch h.residents where h.id = :id")
    public Optional<Hut> findById(@Param("id") Long id);
}
