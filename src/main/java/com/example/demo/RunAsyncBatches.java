package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by georgi.peychev on 8/9/17.
 */
@Component
public class RunAsyncBatches implements CommandLineRunner {

  private BatchTask runAsyncBatch;
  private TaskLoader taskLoader;

  @Autowired
  public RunAsyncBatches(BatchTask runAsyncBatch, TaskLoader taskLoader) {
    this.runAsyncBatch = runAsyncBatch;
    this.taskLoader = taskLoader;
  }

  @Override
  public void run(String... args) throws Exception {

    taskLoader.createTasks(1000);

    runBatches(4);
  }

  private void runBatches(int batchCount) throws Exception {
    for (Integer i = 0; i < batchCount; i++) {
      runAsyncBatch.runBatch("B"+batchCount);
    }
  }

}
