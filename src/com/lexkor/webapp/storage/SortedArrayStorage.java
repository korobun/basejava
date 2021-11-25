package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        int insertKey = -findIndex(r.toString()) - 1;
        System.arraycopy(storage, insertKey, storage, insertKey + 1, size - insertKey);
        storage[insertKey] = r;
        size++;
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = findIndex(uuid);
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }

    @Override
    protected int findIndex(String uuid) {
        Resume keyResume = new Resume();
        keyResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, keyResume);
    }
}