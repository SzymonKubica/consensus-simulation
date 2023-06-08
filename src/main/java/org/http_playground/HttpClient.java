package org.http_playground;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClient {

    @Getter
    private String host;

    @Getter
    private int port;

    @Setter
    @Getter
    private String endpoint;

    public HttpClient(String host, int port, String endpoint) {
        this.host = host;
        this.port = port;
        this.endpoint = endpoint;
    }

    public void sendGetRequest(Map<String, String> parameters) {
        try {
            URL url = new URL(String.format("http://%s:%d/%s?%s", this.host, this.port, this.endpoint, ParameterStringBuilder.getParamsString(parameters)));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);

        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
private class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
}

