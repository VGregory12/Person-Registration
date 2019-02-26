package com.agat.test.repos;



import com.agat.test.domain.Agat2Users;
import org.springframework.data.repository.CrudRepository;

public interface Agat2UsersRepo extends CrudRepository <Agat2Users, String> {
    Iterable<Agat2Users> findByUsername (String user_name);
}
