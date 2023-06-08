package org.big_query_playground;

public class Main {
    public static void main(String[] args) {
        BigQueryFileLoader fileLoader = new BigQueryFileLoader(null, null, null);
        fileLoader.testLoadFile();
    }
}
