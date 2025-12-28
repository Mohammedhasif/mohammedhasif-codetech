import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class SimpleApiClient {

    public static void main(String[] args) {
        try {

            String apiUrl = "https://6939a427c8d59937aa089667.mockapi.io/data/hasifdata";           
            
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Your API returns an array, so parse it
            JSONArray arr = new JSONArray(response.toString());
            JSONObject json = arr.getJSONObject(0);

            System.out.println("User Details:");
            System.out.println("Name        : " + json.getString("name"));
            System.out.println("Username    : " + json.getString("username"));
            System.out.println("Email       : " + json.getString("email"));
            System.out.println("Street      : " + json.getString("street"));
            System.out.println("Zipcode     : " + json.getString("zipcode"));
            System.out.println("City        : " + json.getString("city"));
            System.out.println("Company     : " + json.getString("companyname"));
            System.out.println("Website     : " + json.getString("website"));
            System.out.println("Catchphrase : " + json.getString("catchphrase"));

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}