import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class FoodData {

    private static final String FDC_SEARCH_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=e0e55U0zL88yidcgIvpgrxbPzuH6RuNHpYNqkf79&query={0}";
    private static final String FDC_PRODUCT_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?api_key=e0e55U0zL88yidcgIvpgrxbPzuH6RuNHpYNqkf79&query={0}";

    private FoodData () {}

    private static String sendRequest(String URL, String foodName) {
        String request = MessageFormat.format(URL, foodName.replace(" ", "%20%"));

        try {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(50000);
            connection.setReadTimeout(50000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            return content.toString();
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String parseAnswer(String requestAnswer) {
        JSONObject obj = new JSONObject(requestAnswer);
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        return null;
    }

    public static String sumCalories(String foodName) {
        String requestAnswer = sendRequest(FDC_SEARCH_URL, foodName);
        String parsedJSON = parseAnswer(requestAnswer);
        return null;
    }

    // public static String
}
