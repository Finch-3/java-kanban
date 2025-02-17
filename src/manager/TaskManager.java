package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;

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

    ArrayList<Subtask> getSubtaskList(int epicId);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    ArrayList<Task> getHistory();
}
