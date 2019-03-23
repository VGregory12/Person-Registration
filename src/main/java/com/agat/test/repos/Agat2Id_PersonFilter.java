package com.agat.test.repos;



import com.agat.test.domain.Agat2IdPerson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface Agat2Id_PersonFilter extends CrudRepository<Agat2IdPerson, Long> {
    Iterable<Agat2IdPerson> findByPid (Integer pid);

}
