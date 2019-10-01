package com.agat.test.repos;

import com.agat.test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByActivationCode(String code);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User SET emailactiv = 1 WHERE activationCode = :code")
//    void updateUser(@Param("code") String code);
}
