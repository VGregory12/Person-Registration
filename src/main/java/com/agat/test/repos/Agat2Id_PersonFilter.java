package com.agat.test.repos;



import com.agat.test.domain.Agat2IdPerson;
import org.springframework.data.repository.CrudRepository;

public interface Agat2Id_PersonFilter extends CrudRepository<Agat2IdPerson, Long> {
    Iterable<Agat2IdPerson> findByPid (Integer pid);
}
