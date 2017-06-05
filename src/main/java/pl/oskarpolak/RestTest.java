package pl.oskarpolak;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestTest {
    public static void main(String[] args) {

        try {
            // Otwieramy połączenie HTTP
            URL url = new URL("http://localhost:8080/api/user/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Access-Password", "akademiakodujestfajna");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }


             System.out.println(stringBuilder.toString());

            JSONArray array = new JSONArray(stringBuilder.toString());

            for(int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                System.out.println("Username: " + object.getString("username"));
                System.out.println("Password: " + object.getString("password"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}