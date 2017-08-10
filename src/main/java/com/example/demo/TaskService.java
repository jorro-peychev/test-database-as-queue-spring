package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.domain.TaskHistory;
import com.example.demo.repository.LockTaskRepository;
import com.example.demo.repository.TaskHistoryRepository;
import com.example.demo.repository.TaskRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);
  private TaskRepository taskRepository;
  private LockTaskRepository lockTaskRepository;
  private TaskHistoryRepository taskHistoryRepository;

  public TaskService(TaskRepository taskRepository,
      LockTaskRepository lockTaskRepository,
      TaskHistoryRepository taskHistoryRepository) {
    this.taskRepository = taskRepository;
    this.lockTaskRepository = lockTaskRepository;
    this.taskHistoryRepository = taskHistoryRepository;
  }

  //////////////////


  public List<Task> getTasks(int page, int pageSize, String batch) {
    LOGGER.info(batch + "==============>>>>start page = {}", page);
    lockTaskRepository.lockTable();

    Page<Task> tasks = taskRepository.searchPendingTasks(new PageRequest(page, pageSize));
    LOGGER.info(batch + "==============>>>>tasks in page = {}", tasks.getContent());

    tasks.getContent().stream().forEach(t -> t.setLockDate(LocalDateTime.now()));
    taskRepository.save(tasks.getContent());

    return tasks.getContent();
  }

  public void processTask(Task task, String batch) {
    TaskHistory taskHistory = new TaskHistory();
    taskHistory.setTaskId(task.getId());
    taskHistory.setName(task.getName());

    taskHistoryRepository.save(taskHistory);
    taskRepository.delete(task);
    LOGGER.info(batch + "==============>>>>finished task = {}", task.getName());
  }


  public List<Task> searchPendingWithLock(int page, int pageSize) {
    Page<Task> tasks = taskRepository.searchPendingWithLock(new PageRequest(page, pageSize));
    return tasks.getContent();
  }

  public List<Task> searchPendingWithoutLock(int page, int pageSize) {
    Page<Task> tasks = taskRepository.searchPendingWithLock(new PageRequest(page, pageSize));
    return tasks.getContent();
  }

}
