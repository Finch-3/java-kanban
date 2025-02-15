package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task);

    void updateTask(Task task);

    void clearTask();

    Task getTaskById(int taskId);

    void deleteTaskById(int taskId);

    void addEpic(Epic epic);

    void updateEpic(Epic epic);

    void clearEpics();

    Epic getEpicById(int epicId);

    Subtask getSubtaskById(int subtaskId);

    void clearSubtask();

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void deleteSubtaskById(int subtaskId);

    void deleteEpicById(int epicId);

    ArrayList<Subtask> getSubtaskList(int epicId);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();
}
