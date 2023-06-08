package org.big_query_playground;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

public class BigQueryFileLoader {

    @Getter
    private final String projectId;

    @Setter
    @Getter
    private String datasetName;

    @Setter
    @Getter
    private String tableName;

    public BigQueryFileLoader(String projectId, String datasetName, String tableName) {
        this.projectId = projectId;
        this.datasetName = datasetName;
        this.tableName = tableName;
    }

    public void testLoadFile() {
        User user = new User("Szymon", "Kubica", 22, "+44 07379975910", Gender.Male);
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        System.out.println(userJson);

    }

}
