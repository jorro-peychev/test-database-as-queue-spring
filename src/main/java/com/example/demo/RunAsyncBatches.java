package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by georgi.peychev on 8/9/17.
 */
@Component
public class RunAsyncBatches implements CommandLineRunner {

  private BatchTask runAsyncBatch1;
  private BatchTask runAsyncBatch2;
  private BatchTask runAsyncBatch3;
  private BatchTask runAsyncBatch4;

  private TaskLoader taskLoader;

  @Autowired
  public RunAsyncBatches(BatchTask runAsyncBatch1,
      BatchTask runAsyncBatch2, BatchTask runAsyncBatch3,
      BatchTask runAsyncBatch4, TaskLoader taskLoader) {
    this.runAsyncBatch1 = runAsyncBatch1;
    this.runAsyncBatch2 = runAsyncBatch2;
    this.runAsyncBatch3 = runAsyncBatch3;
    this.runAsyncBatch4 = runAsyncBatch4;

    this.taskLoader = taskLoader;
  }

  @Override
  public void run(String... args) throws Exception {
    taskLoader.createTasks(1000);

    runAsyncBatch1.runBatch("B1");
    runAsyncBatch2.runBatch("B2");
    runAsyncBatch3.runBatch("B3");
    runAsyncBatch4.runBatch("B4");
  }


}
