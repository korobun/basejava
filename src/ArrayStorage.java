/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    void save(Resume r) {
        if (storageSize < storage.length) {
            storage[storageSize] = r;
            storageSize++;
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r!= null && r.toString() == uuid) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = 0;

        for (int i = 0; i < storageSize; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                index = i;

                if (index < storageSize - 1) {
                    for (int j = index + 1; j < storageSize; j++) {
                        storage[j - 1] = storage[j];
                    }
                }
                storageSize--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allValidResumes = new Resume[storageSize];
        System.arraycopy (storage, 0, allValidResumes, 0, storageSize);
        return allValidResumes;
    }

    int size() {
        return storageSize;
    }
}
