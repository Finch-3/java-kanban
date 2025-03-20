package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

class InMemoryHistoryManagerTest {

    TaskManager manager = Manager.getDefault();

    @Test
    void addHistoryTaskEpicSubtaskNoRepeatDelete() {
        Task task = new Task("Задача", "Описание");
        Epic epic = new Epic("Эпик", "Описание эпика");
        manager.addTask(task);
        manager.addEpic(epic);

        Subtask subtask = new Subtask(epic.getId(), "Подзадача", "Описание подзадачи");

        manager.addSubtask(subtask);

        manager.getTaskById(task.getId());
        manager.getTaskById(task.getId());
        manager.getEpicById(epic.getId());
        manager.getEpicById(epic.getId());
        manager.getSubtaskById(subtask.getId());
        manager.getSubtaskById(subtask.getId());

        Assertions.assertEquals(3, manager.getHistory().size(), "Задачи повторяются");

        manager.deleteTaskById(task.getId());

        Assertions.assertEquals(2, manager.getHistory().size(), "Задача не удалена");

        manager.deleteEpicById(epic.getId());

        Assertions.assertEquals(0, manager.getHistory().size(), "Задача не удалена");

    }
}