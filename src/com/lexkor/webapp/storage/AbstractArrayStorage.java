package com.lexkor.webapp.storage;

import com.lexkor.webapp.exception.ExistStorageException;
import com.lexkor.webapp.exception.NotExistStorageException;
import com.lexkor.webapp.exception.StorageException;
import com.lexkor.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume r) {
        Resume resume = Objects.requireNonNull(r, "Resume must not be null");

        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }

        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: storage overflow", uuid);
        }

        saveResume(resume, index);
        size++;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);

        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public final void update(Resume resume) {
        Resume r = Objects.requireNonNull(resume, "Resume must not be null");

        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }

        storage[index] = r;
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}