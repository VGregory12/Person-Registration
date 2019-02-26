package com.agat.test.repos;

import com.agat.test.domain.Agat2IdPerson;

import org.springframework.data.repository.CrudRepository;

public interface Agat2SearchFilter extends CrudRepository<Agat2IdPerson, String> {
    Iterable<Agat2IdPerson> findByAgat2PersonSurnameLikeOrAgat2PersonNameLike (String surname, String name);
}
