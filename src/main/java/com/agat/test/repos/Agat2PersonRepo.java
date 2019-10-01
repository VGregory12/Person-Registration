package com.agat.test.repos;

import com.agat.test.domain.Agat2IdPerson;
import com.agat.test.domain.Agat2Person;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface Agat2PersonRepo extends CrudRepository<Agat2Person, Integer> {
    @Transactional
    @Modifying
    @Query("Delete FROM Agat2IdPerson WHERE pid = 41")
    void deleteByPid();
}
