package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager taskManager = Manager.getDefault();

    Task task = new Task("Задача", "Описание");
    Epic epic = new Epic("Эпик", "Описание эпика");

    @Test
    void addGetPrintDeleteTask() {
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");

        taskManager.deleteTaskById(taskId);

        assertNull(taskManager.getTaskById(taskId));
        assertEquals(0, taskManager.getTasks().size(), "Неверное количество задач.");
    }

    @Test
    void updateDeleteListTask() {
        Task updateTask = new Task(taskManager.addTask(task), "Задача", "Описание", Status.IN_PROGRESS);
        final int taskId = taskManager.updateTask(updateTask);

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(updateTask, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(updateTask, tasks.get(0), "Задачи не совпадают.");

        taskManager.clearTask();

        assertEquals(0, taskManager.getTasks().size(), "Неверное количество задач.");
    }

    @Test
    void addGetPrintDeleteEpic() {
        final int epicId = taskManager.addEpic(epic);

        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(epic, savedEpic, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");

        taskManager.deleteEpicById(epicId);

        assertNull(taskManager.getEpicById(epicId));
        assertEquals(0, taskManager.getEpics().size(), "Неверное количество задач.");
    }

    @Test
    void updateDeleteListEpic() {
        Epic updateEpic = new Epic(taskManager.addEpic(epic), "Эпик", "Описание");
        final int epicId = taskManager.updateEpic(updateEpic);

        final Epic savedEpic = taskManager.getEpicById(epicId);

        assertNotNull(savedEpic, "Задача не найдена.");
        assertEquals(updateEpic, savedEpic, "Задачи не совпадают.");

        final List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(updateEpic, epics.get(0), "Задачи не совпадают.");

        taskManager.clearEpics();

        assertEquals(0, taskManager.getEpics().size(), "Неверное количество задач.");
    }

    @Test
    void addGetPrintDeleteSubtask() {
        Subtask subtask = new Subtask(taskManager.addEpic(epic), "Подзадача", "Описание подзадачи");

        final int subtaskId = taskManager.addSubtask(subtask);

        final Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают.");

        final List<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.get(0), "Задачи не совпадают.");

        taskManager.deleteSubtaskById(subtaskId);

        assertNull(taskManager.getSubtaskById(subtaskId));
        assertEquals(0, taskManager.getSubtasks().size(), "Неверное количество задач.");
    }

    @Test
    void updateDeleteListSubtask() {
        Subtask subtask = new Subtask(taskManager.addEpic(epic), "Подзадача", "Описание подзадачи");
        Subtask updateSubtask = new Subtask(taskManager.addSubtask(subtask), "Подзадача",
                "Описание", Status.IN_PROGRESS);
        final int subtaskId = taskManager.updateSubtask(updateSubtask);

        final Subtask savedSubtask = taskManager.getSubtaskById(subtaskId);

        assertNotNull(savedSubtask, "Задача не найдена.");
        assertEquals(updateSubtask, savedSubtask, "Задачи не совпадают.");

        final List<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Задачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(updateSubtask, subtasks.get(0), "Задачи не совпадают.");

        taskManager.clearSubtask();

        assertEquals(0, taskManager.getSubtasks().size(), "Неверное количество задач.");
        assertEquals(0, epic.getSubtaskList().size(), "Неверное количество задач.");
    }

    @Test
    void addHistoryCopyObject() {
        taskManager.addTask(task);
        Subtask subtask = new Subtask(taskManager.addEpic(epic), "Подзадача", "Описание подзадачи");
        taskManager.addSubtask(subtask);

        taskManager.getTaskById(task.getId());
        taskManager.getEpicById(epic.getId());
        taskManager.getSubtaskById(subtask.getId());

        assertEquals(3, taskManager.getHistory().size(), "Размер списка не 3");

        Task updateTask = new Task(task.getId(), "Задача обновление",
                "Описание обновление", Status.IN_PROGRESS);

        Epic updateEpic = new Epic(epic.getId(), "Эпик обновление",
                "Описание Эпик обновление");

        Subtask updateSubtask = new Subtask(subtask.getId(), "Подзадача обновление",
                "Описание Подзадача обновление", Status.IN_PROGRESS);

        assertEquals(task.getName(), taskManager.getHistory().get(0).getName(), "Задачи не совпадают");
        assertEquals(epic.getName(), taskManager.getHistory().get(1).getName(), "Задачи не совпадают");
        assertEquals(subtask.getName(), taskManager.getHistory().get(2).getName(), "Задачи не совпадают");


        taskManager.updateTask(updateTask);
        taskManager.updateEpic(updateEpic);
        taskManager.updateSubtask(updateSubtask);

        assertNotEquals(taskManager.getTasks().get(0).getName(),
                taskManager.getHistory().get(0).getName(), "Задачи совпадают");
        assertNotEquals(taskManager.getEpics().get(0).getName(),
                taskManager.getHistory().get(1).getName(), "Задачи совпадают");
        assertNotEquals(taskManager.getSubtasks().get(0).getName(),
                taskManager.getHistory().get(2).getName(), "Задачи совпадают");
    }

}