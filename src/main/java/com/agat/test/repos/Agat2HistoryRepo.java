package com.agat.test.repos;

import com.agat.test.domain.Agat2History;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Agat2HistoryRepo extends CrudRepository<Agat2History, Integer> {
//    UPDATE AGATMIN.PERSON SET IDENTIFIER='11231PB0' WHERE PID = 1;
//"select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?"

    @Transactional
    @Modifying
    @Query("UPDATE Agat2History SET active = 0 WHERE pid = :pid AND active = 1")
    void updateHistory(@Param("pid") Integer pid);

    }