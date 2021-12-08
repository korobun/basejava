package com.lexkor.webapp.storage;

import com.lexkor.webapp.exception.ExistStorageException;
import com.lexkor.webapp.exception.NotExistStorageException;
import com.lexkor.webapp.exception.StorageException;
import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void save(Resume r) {
        if (r == null) {
            throw new NullPointerException();
        }

        int index = findIndex(r.toString());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }

        if (size == STORAGE_LIMIT) {
            throw new StorageException("ERROR: storage overflow", r.getUuid());
        }

        saveResume(r, index);
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
        if (resume == null) {
            throw new NullPointerException();
        }

        int index = findIndex(resume.toString());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }

        storage[index] = resume;
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