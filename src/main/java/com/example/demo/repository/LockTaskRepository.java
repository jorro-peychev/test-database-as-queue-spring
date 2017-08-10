package com.example.demo.repository;

import com.example.demo.domain.LockTask;
import com.example.demo.domain.Task;
import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by georgi.peychev on 8/10/17.
 */
public interface LockTaskRepository extends CrudRepository<LockTask, Long> {
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT t FROM LockTask t")
  List<LockTask> lockTable();
}
