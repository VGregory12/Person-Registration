package com.agat.test.repos;


import com.agat.test.domain.Agat2Address;

import org.springframework.data.repository.CrudRepository;

public interface Agat2AddressRepo extends CrudRepository <Agat2Address, Integer> {
    Iterable<Agat2Address> findByPid (Integer pid);

}
