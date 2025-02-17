package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Manager.getDefaultHistory();

    @Test
    void addHistoryTaskEpicSubtask() {
        Task task = new Task("Задача", "Описание");
        Epic epic = new Epic("Задача", "Описание");
        Subtask subtask = new Subtask(epic.getId(), "Задача", "Описание");

        historyManager.addTask(task);
        historyManager.addTask(epic);
        historyManager.addTask(subtask);

        final ArrayList<Task> history = historyManager.getHistory();
        Assertions.assertEquals(3, history.size(), "Количество задач не совпадает");

        Assertions.assertEquals(subtask, history.getLast(), "Задача не в конце списка");
    }
}