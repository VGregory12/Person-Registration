package com.agat.test.repos;

import com.agat.test.domain.Agat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface We extends CrudRepository<Agat, Long> {
    Iterable<Agat> findBySurnameOrName(String surname, String name);

}
