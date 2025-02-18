package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.List;

public interface TaskManager {
    int addTask(Task task);

    int updateTask(Task task);

    void clearTask();

    Task getTaskById(int taskId);

    void deleteTaskById(int taskId);

    int addEpic(Epic epic);

    int updateEpic(Epic epic);

    void clearEpics();

    Epic getEpicById(int epicId);

    Subtask getSubtaskById(int subtaskId);

    void clearSubtask();

    int addSubtask(Subtask subtask);

    int updateSubtask(Subtask subtask);

    void deleteSubtaskById(int subtaskId);

    void deleteEpicById(int epicId);

    List<Subtask> getSubtaskList(int epicId);

    List<Task> getTasks();

    List<Epic> getEpics();

    List<Subtask> getSubtasks();

    List<Task> getHistory();
}
