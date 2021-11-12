/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        if (size() < 1000) {
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
        for (int i = 0; i < size(); i++) {
            if (storage[i].toString() == uuid) {
                storage[i] = null;
                index = i;
            }
        }

        if (index < size() - 1) {
            for (int i = index + 1; i < size(); i++) {
                storage[i - 1] = storage[i];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allValidResumes = new Resume[size()];
        for (int i = 0; i < allValidResumes.length; i++) {
            allValidResumes[i] = storage[i];
        }
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
