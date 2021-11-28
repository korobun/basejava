package com.lexkor.webapp.storage;

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
            System.out.println("ERROR: resume does not exist");
            return;
        }

        if (findIndex(r.toString()) >= 0) {
            System.out.printf("ERROR: resume %s present in storage%n", r);
            return;
        }

        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: storage overflow");
            return;
        }

        saveResume(r);
        size++;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return null;
        }
        return storage[index];
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return;
        }

        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public final void update(Resume resume) {
        if (resume == null) {
            System.out.println("ERROR: resume does not exist");
            return;
        }

        int index = findIndex(resume.toString());
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", resume);
            return;
        }

        storage[index] = resume;
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(int index);

    protected abstract int findIndex(String uuid);
}