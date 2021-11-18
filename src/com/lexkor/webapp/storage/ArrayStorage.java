package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

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

        if (size < storage.length) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: storage overflow");
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
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("ERROR: resume %s not present in storage%n", uuid);
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}