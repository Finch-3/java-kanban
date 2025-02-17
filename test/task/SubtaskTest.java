package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void subtaskAreEqualIfIdIsEqual() {
        Subtask subtask1 = new Subtask( 1, "Задача 1", "Описание 1");
        subtask1.setId(1);
        Subtask subtask2 = new Subtask(1, "Задача 2", "Описание 2");
        subtask2.setId(1);

        Assertions.assertEquals(subtask1, subtask2, "Задачи не равны");
    }
}