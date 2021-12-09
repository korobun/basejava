package com.lexkor.webapp.storage;

import com.lexkor.webapp.exception.ExistStorageException;
import com.lexkor.webapp.exception.NotExistStorageException;
import com.lexkor.webapp.exception.StorageException;
import com.lexkor.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.lexkor.webapp.storage.AbstractArrayStorage.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    final void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    final void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    final void save() {
        String UUID_4 = "uuid4";
        Resume r = new Resume(UUID_4);
        storage.save(r);
        assertEquals(r, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test
    final void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_3)));
    }

    @Test
    final void testOverflow() {
        storage.clear();

        for (int i = 0; i < STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                fail("Overflow occurred ahead of time", e.getCause());
            }
        }

        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    final void get() {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test
    final void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    final void delete() {
        storage.delete(UUID_1);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
        assertEquals(2, storage.size());
    }

    @Test
    final void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    final void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test
    final void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    final void getAll() {
        Resume[] arrResumes = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(arrResumes, storage.getAll());
    }

    @Test
    final void size() {
        assertEquals(3, storage.size());
    }
}