package Services;

import DAOs.DataDAO;

public class PersistenceService {
    private static DataDAO data;

    static {
        data = new DataDAO("default", 0, false);
    }

    public static DataDAO getData() {
        return data;
    }

    public static void setData(DataDAO d) {
        data = d;
    }
}
