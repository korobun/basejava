package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndex(r.toString()) >= 0) {
            System.out.println("ERROR: this resume present in storage");
            return;
        }

        if (r != null && size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with this uuid not present in storage");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume with this uuid not present in storage");
            return;
        }

        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.toString());
        if (index < 0) {
            System.out.println("ERROR: this resume not present in storage");
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString() == uuid) {
                return i;
            }
        }
        return -1;
    }
}