package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (r == null) {
            System.out.println("ERROR: resume does not exist");
            return;
        }

        if (findIndex(r.toString()) >= 0) {
            System.out.printf("ERROR: resume %s present in storage%n", r);
            return;
        }

        if (size < STORAGE_LIMIT) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR: storage overflow");
        }
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