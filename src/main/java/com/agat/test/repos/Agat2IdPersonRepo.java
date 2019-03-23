package com.agat.test.repos;

import com.agat.test.domain.Agat2IdPerson;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Agat2IdPersonRepo extends CrudRepository<Agat2IdPerson, Integer> {
    @Transactional
//    @Modifying
//    @Query("Delete FROM Agat2IdPerson WHERE pid = :pid ")
    void deleteByPid(Integer pid);

    Iterable<Agat2IdPerson> findByPid(Integer pid);

}