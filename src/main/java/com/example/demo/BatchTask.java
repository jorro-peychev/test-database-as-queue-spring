package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.repository.LockTaskRepository;
import com.example.demo.repository.TaskHistoryRepository;
import com.example.demo.repository.TaskRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class BatchTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(BatchTask.class);
  private TaskService taskService;


  public BatchTask(TaskService taskService) {
    this.taskService = taskService;
  }

  @Async
  public void runBatch(String batchName) throws Exception {
    LOGGER.info(batchName + "==============>>>>start batch = {}", batchName);
    int page = 0;
    int pageSize = 3;
    int taskCount = 3;

    while (taskCount == pageSize) {

      List<Task> tasks = taskService.getTasks(page, pageSize, batchName);
      tasks.stream().forEach(t -> taskService.processTask(t, batchName));
      taskCount = tasks.size();
    }
  }
}
