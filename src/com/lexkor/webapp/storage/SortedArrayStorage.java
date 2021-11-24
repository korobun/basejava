package com.lexkor.webapp.storage;

import com.lexkor.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {

    }

    @Override
    protected int findIndex(String uuid) {
        Resume keyResume = new Resume();
        keyResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, keyResume);
    }
}
