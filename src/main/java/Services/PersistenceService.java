package Services;

import dtos.DataDto;

public class PersistenceService {
    private static DataDto data;

    static {
        data = new DataDto("default", 0, false);
    }

    public static DataDto getData() {
        return data;
    }

    public static void setData(DataDto d) {
        data = d;
    }
}
