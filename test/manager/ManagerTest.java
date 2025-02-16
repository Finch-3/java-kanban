package manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    public void taskManagerTest() {
        Assertions.assertNotNull(Manager.getDefault());
    }

    @Test
    public void historyManagerTest() {
        Assertions.assertNotNull(Manager.getDefaultHistory());
    }
}