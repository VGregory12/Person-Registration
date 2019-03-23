package com.agat.test.repos;


import com.agat.test.domain.Agat2Address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Agat2AddressRepo extends CrudRepository <Agat2Address, Integer> {
    Iterable<Agat2Address> findByPid (Integer pid);

    @Transactional
    @Modifying
    @Query("Delete FROM Agat2Address WHERE pid = :pid")
    void deleteByPid(@Param("pid") Integer pid);

}
