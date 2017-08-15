package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by georgi.peychev on 8/9/17.
 */
@Component
public class RunAsyncBatches implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(RunAsyncBatches.class);
  private BatchTask runAsyncBatch;
  private TaskLoader taskLoader;
  //private TaskServiceBatch taskServiceBatch;
  private TaskService taskService;

  @Autowired
  public RunAsyncBatches(BatchTask runAsyncBatch, TaskLoader taskLoader,/*,
      TaskServiceBatch taskServiceBatch*/TaskRepository taskRepository, TaskService taskService) {

    this.runAsyncBatch = runAsyncBatch;
    this.taskLoader = taskLoader;
    this.taskService = taskService;
    //this.taskServiceBatch = taskServiceBatch;
//    this.taskRepository = taskRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    taskLoader.createTasks(1000);

    runBatches(4);
  }

  private void runBatches(int batchCount) throws Exception {

    for (Integer i = 0; i < batchCount; i++) {
      runAsyncBatch.runBatch("B" + i);
    }
  }

}
