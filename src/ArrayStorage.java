/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (r != null && size < storage.length) {
            storage[size] = r;
            size++;
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

        for (int i = 0; i < size; i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                index = i;

                if (index < size - 1) {
                    for (int j = index + 1; j < size; j++) {
                        storage[j - 1] = storage[j];
                    }
                }
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allValidResumes = new Resume[size];
        System.arraycopy (storage, 0, allValidResumes, 0, size);
        return allValidResumes;
    }

    int size() {
        return size;
    }
}
