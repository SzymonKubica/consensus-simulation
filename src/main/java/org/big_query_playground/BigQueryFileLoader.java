package org.big_query_playground;

import com.google.cloud.bigquery.*;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
        BigQuery bigQuery = BigQueryOptions.getDefaultInstance().getService();
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT CONCAT('https://stackoverflow.com/questions/', "
                                        + "CAST(id as STRING)) as url, view_count "
                                        + "FROM `bigquery-public-data.stackoverflow.posts_questions` "
                                        + "WHERE tags like '%google-bigquery%' "
                                        + "ORDER BY view_count DESC "
                                        + "LIMIT 10")
                        // Use standard SQL syntax for queries.
                        // See: https://cloud.google.com/bigquery/sql-reference/
                        .setUseLegacySql(false)
                        .build();

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        // Wait for the query to complete.
        try {
            queryJob = queryJob.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check for errors
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        // Get the results.
        TableResult result = null;
        try {
            result = queryJob.getQueryResults();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Print all pages of the results.
        for (FieldValueList row : result.iterateAll()) {
            // String type
            String url = row.get("url").getStringValue();
            String viewCount = row.get("view_count").getStringValue();
            System.out.printf("%s : %s views\n", url, viewCount);
        }
    }
}
