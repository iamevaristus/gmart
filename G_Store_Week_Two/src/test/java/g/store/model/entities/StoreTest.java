package g.store.model.entities;

import g.store.exception.StoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    Store store;

    @BeforeEach
    void apply() {
        store = new Store();
    }

    @Test
    void checkIfTheStoreHasAnyCashier() {
        assertEquals(
                false,
                store.hasCashier()
        );
    }

    @Test
    void checkIfTheStoreHasAManager() {
        assertEquals(
                false,
                store.hasManager()
        );
    }

    @Test
    void shouldAddFeedbackFromAnyoneToTheListOfStoreFeedbacks() {
        try {
            assertInstanceOf(List.class, store.getStoreProducts());
        } catch (StoreException e) {
            throw new RuntimeException(e);
        }
    }
}