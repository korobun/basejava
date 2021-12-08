package com.lexkor.webapp.storage;

import com.lexkor.webapp.exception.ExistStorageException;
import com.lexkor.webapp.exception.NotExistStorageException;
import com.lexkor.webapp.exception.StorageException;
import com.lexkor.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    final void save() {
        String UUID_4 = "uuid4";
        storage.save(new Resume(UUID_4));
    }

    @Test
    final void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(new Resume(UUID_3));
        });
    }

    @Test
    final void testOverflow() throws NoSuchFieldException, IllegalAccessException {
        storage.clear();
        int maxSize = (int) AbstractArrayStorage.class.getDeclaredField("STORAGE_LIMIT").get(storage);

        for (int i = 0; i < maxSize; i++) {
            try {
                storage.save(new Resume());
            } catch (StorageException e) {
                Assertions.fail(e.getMessage(), e.getCause());
            }
        }

        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume());
        });
    }

    @Test
    final void get() {
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
        Assertions.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
        Assertions.assertEquals(new Resume(UUID_3), storage.get(UUID_3));
    }

    @Test
    final void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    final void delete() {
        storage.delete(UUID_1);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_1);
        });

        storage.delete(UUID_2);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_2);
        });

        storage.delete(UUID_3);
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_3);
        });
    }

    @Test
    final void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    final void update() {
        storage.update(new Resume(UUID_1));
        storage.update(new Resume(UUID_2));
        storage.update(new Resume(UUID_3));
    }

    @Test
    final void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(new Resume("dummy"));
        });
    }

    @Test
    final void getAll() {
        int size = storage.size();

        Resume[] arrResumes = new Resume[size];
        try {
            arrResumes = storage.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage(), e.getCause());
        }

        for (int i = 0; i < size; i++) {
            Assertions.assertEquals(arrResumes[i], storage.get(arrResumes[i].getUuid()));
        }
    }

    @Test
    final void size() {
        Assertions.assertEquals(3, storage.size());
    }
}