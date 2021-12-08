package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void saveResume(Resume r, int index) {
        int insertKey = -index - 1;
        System.arraycopy(storage, insertKey, storage, insertKey + 1, size - insertKey);
        storage[insertKey] = r;
    }

    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    protected int findIndex(String uuid) {
        Resume keyResume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, keyResume);
    }
}