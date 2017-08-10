package com.example.demo.repository;

import com.example.demo.domain.Task;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by georgi.peychev on 8/8/17.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

//  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Lock(LockModeType.PESSIMISTIC_READ)
  @Query("SELECT t FROM Task t where status='pending'")
  Page<Task> searchPendingWithLock(Pageable pageRequest);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Task findById(Long id);

  @Query("SELECT t FROM Task t where status='pending'")
  Page<Task> searchPendingWithoutLock(Pageable pageRequest);

  @Query("SELECT t FROM Task t WHERE lockDate IS NULL")
  Page<Task> searchPendingTasks(Pageable pageRequest);
}
