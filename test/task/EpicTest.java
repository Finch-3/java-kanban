package task;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    public void epicAreEqualIfIdIsEqual() {
        Epic epic1 = new Epic("Задача 1", "Описание 1");
        epic1.setId(1);
        Epic epic2 = new Epic("Задача 2", "Описание 2");
        epic2.setId(1);

        Assertions.assertEquals(epic1, epic2, "Задачи не равны");
    }
    @Test
    public void ThereIsNoCollusionInId() {
        TaskManager manager = new InMemoryTaskManager();
        Epic epic1 = new Epic("Задача 1", "Описание 1");
        manager.addEpic(epic1);
        Epic epic2 = new Epic("Задача 2", "Описание 2");
        manager.addEpic(epic2);
        Assertions.assertNotEquals(epic1, epic2, "Задачи не равны");
    }
}