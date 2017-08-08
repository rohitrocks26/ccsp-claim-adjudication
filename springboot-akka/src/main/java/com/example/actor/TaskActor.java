package com.example.actor;

import javax.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import com.example.domain.Task;
import com.example.repository.TaskRepository;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;

@SuppressWarnings("deprecation")
@Scope("prototype")
public class TaskActor extends UntypedActor{

    private TaskRepository taskRepository;

    public static Props props(final TaskRepository taskRepository) {
        return Props.create(new Creator<TaskActor>() {
            private static final long serialVersionUID = 1L;
            @Override
            public TaskActor create() throws Exception {
                return new TaskActor(taskRepository);
            }
        });
    }

    public TaskActor(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }


    @Override
    @Transactional
    public void onReceive(Object message) throws Exception {
        Task task = (Task) message;
        System.out.println(task.getName());
        //taskRepository.save(task);

    }

}
