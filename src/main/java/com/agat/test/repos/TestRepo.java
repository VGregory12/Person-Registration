package com.agat.test.repos;

import com.agat.test.domain.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepo extends CrudRepository<Test, Integer> {
}
