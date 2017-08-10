package com.example.demo.repository;

import com.example.demo.domain.TaskHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by georgi.peychev on 8/8/17.
 */
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

  TaskHistory findByTaskId(Long taskId);
}
