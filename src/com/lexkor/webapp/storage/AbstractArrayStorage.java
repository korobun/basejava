package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    final public void save(Resume r) {
        if (r == null) {
            System.out.println("ERROR: resume does not exist");
            return;
        }

        if (findIndex(r.toString()) >= 0) {
            System.out.printf("ERROR: resume %s present in storage%n", r);
            return;
        }

        saveResume(r);
    }


    final public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return null;
        }
        return storage[index];
    }

    final public void delete(String uuid) {
        if (findIndex(uuid) < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return;
        }

        deleteResume(uuid);
    }

    final public void update(Resume resume) {
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

    final public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    final public int size() {
        return size;
    }

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(String uuid);

    protected abstract int findIndex(String uuid);
}