package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        super.save(r);

        if (size < STORAGE_LIMIT) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: storage overflow");
        }
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);

        storage[findIndex(uuid)] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}