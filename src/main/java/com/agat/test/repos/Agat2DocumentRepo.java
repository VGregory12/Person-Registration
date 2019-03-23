package com.agat.test.repos;

        import com.agat.test.domain.Agat2Document;


        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.transaction.annotation.Transactional;

public interface Agat2DocumentRepo extends CrudRepository <Agat2Document, Integer> {
    Iterable<Agat2Document> findByPid (Integer pid);

    @Transactional
    @Modifying
    @Query("Delete FROM Agat2Document WHERE pid = :pid")
    void deleteByPid(@Param("pid") Integer pid);
}
