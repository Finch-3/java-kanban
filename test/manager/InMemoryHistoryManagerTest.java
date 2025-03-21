package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.LinkedHashSet;

class InMemoryHistoryManagerTest {

    TaskManager manager = Manager.getDefault();

    @Test
    void addHistoryTaskEpicSubtaskNoRepeatDelete() {
        Task task = new Task("Задача", "Описание");
        Task task1 = new Task("Задача 1", "Описание 1");

        manager.addTask(task);
        manager.addTask(task1);

        Epic epic = new Epic("Эпик", "Описание эпика");
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");

        manager.addEpic(epic);
        manager.addEpic(epic1);

        Subtask subtask = new Subtask(epic.getId(), "Подзадача", "Описание подзадачи");
        Subtask subtask1 = new Subtask(epic.getId(), "Подзадача 1", "Описание подзадачи 1");
        Subtask subtask2 = new Subtask(epic.getId(), "Подзадача 2", "Описание подзадачи 2");

        manager.addSubtask(subtask);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        manager.getTaskById(task.getId());
        manager.getTaskById(task1.getId());

        manager.getEpicById(epic.getId());
        manager.getEpicById(epic1.getId());

        manager.getSubtaskById(subtask.getId());
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask2.getId());

        Assertions.assertEquals(7, new LinkedHashSet<>(manager.getHistory()).size(),
                "Задачи повторяются");

        manager.getTaskById(task.getId());
        manager.getEpicById(epic.getId());

        manager.getTaskById(task1.getId());
        manager.getEpicById(epic1.getId());

        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask.getId());
        manager.getSubtaskById(subtask2.getId());

        Assertions.assertEquals(7, new LinkedHashSet<>(manager.getHistory()).size(),
                "Задачи повторяются");

        manager.deleteTaskById(task.getId());

        Assertions.assertFalse(manager.getHistory().contains(task));

        manager.deleteEpicById(epic.getId());

        Assertions.assertFalse(manager.getHistory().contains(epic));
        Assertions.assertFalse(manager.getHistory().contains(subtask));
        Assertions.assertFalse(manager.getHistory().contains(subtask1));
        Assertions.assertFalse(manager.getHistory().contains(subtask2));

        Assertions.assertEquals(2, manager.getHistory().size(), "Задачи не удалились");
    }
}