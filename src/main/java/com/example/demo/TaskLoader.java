package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.repository.TaskRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class TaskLoader {

  private TaskRepository taskRepository;

  public TaskLoader(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }


  public void createTasks(int countTasks) {
    for (Integer i = 0; i < countTasks; i++) {
      Task task = new Task();
      task.setName(i.toString());
      taskRepository.save(task);
    }
  }
}
