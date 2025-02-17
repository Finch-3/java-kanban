package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager taskManager = Manager.getDefault();

    Task task = new Task("Задача", "Описание");
    Epic epic = new Epic("Эпик", "Описание эпика");
    Subtask subtask = new Subtask(1, "Подзадача", "Описание подзадачи");

    @Test
    void addGetPrintTask() {
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final ArrayList<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void addGetPrintEpic() {
        final int epicId = taskManager.addEpic(epic);

        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final ArrayList<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");
    }

    @Test
    void addGetPrintSubtask() {
        taskManager.addEpic(epic);
        final int subtaskId = taskManager.addSubtask(subtask);

        final Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        final ArrayList<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.get(0), "Задачи не совпадают.");
    }


}