package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.List;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Manager.getDefaultHistory();

    @Test
    void addHistoryTaskEpicSubtaskAndMaxSize() {
        Task task = new Task("Задача", "Описание");
        Epic epic = new Epic("Задача", "Описание");
        Subtask subtask = new Subtask(epic.getId(), "Задача", "Описание");

        historyManager.addTask(task);
        historyManager.addTask(epic);
        historyManager.addTask(subtask);

        final List<Task> history = historyManager.getHistory();
        Assertions.assertEquals(3, history.size(), "Количество задач не совпадает");

        Assertions.assertEquals(subtask, history.getLast(), "Задача не в конце списка");

        for (int i = 0; i < 15; i++) {
            historyManager.addTask(task);
        }
        Assertions.assertEquals(10, historyManager.getHistory().size(), "Список не равен десяти");
    }
}