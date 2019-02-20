package com.agat.test.repos;

import com.agat.test.domain.Agat2Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Agat2PersonFilter extends CrudRepository <Agat2Person, String>{
//        Iterable<Agat2Person> findBySurname (String surname);

// по букве
//        @Query(value = "SELECT * FROM AGATMIN.PERSON WHERE agatmin.person.NAME like %?1%", nativeQuery = true)
//        Iterable<Agat2Person> findByNameLike(String name);


        Iterable<Agat2Person> findByNameLike(String name);

//        @Query(value = "SELECT * FROM AGATMIN.PERSON WHERE agatmin.person.NAME like '%name%'", nativeQuery = true)
//        Iterable<Agat2Person> findByNameLike(@Param("name") String name);
//
////        @Query("Select c from Registration c where c.place LIKE  %?1%")
//        List<Registration> findPlaceContainingKeywordAnywhere(String place);
}
