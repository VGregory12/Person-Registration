package com.agat.test.repos;

        import com.agat.test.domain.Agat;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

public interface AgatRepo extends CrudRepository<Agat, Integer> {

}
