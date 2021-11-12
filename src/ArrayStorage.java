/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int storageSize = size();
        for (int i = 0; i < storageSize; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size() < storage.length) {
            storage[size()] = r;
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
        int storageSize = size();

        for (int i = 0; i < storageSize; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                index = i;
            }
        }

        if (index < storageSize - 1) {
            for (int i = index + 1; i < storageSize; i++) {
                storage[i - 1] = storage[i];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int storageSize = size();
        Resume[] allValidResumes = new Resume[storageSize];
        System.arraycopy (storage, 0, allValidResumes, 0, storageSize);
        return allValidResumes;
    }

    int size() {
        int size = 0;
        for (Resume r : storage) {
            if (r != null) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }
}
