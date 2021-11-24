package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (r == null) {
            System.out.println("ERROR: resume does not exist");
            return;
        }

        if (findIndex(r.toString()) >= 0) {
            System.out.printf("ERROR: resume %s present in storage%n", r);
            return;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        if (findIndex(uuid) < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return;
        }
    }

    public void update(Resume resume) {
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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    protected abstract int findIndex(String uuid);
}
