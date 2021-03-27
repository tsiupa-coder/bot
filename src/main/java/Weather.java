import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {


    public static String getWeather(String message, Model model) throws IOException {

        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message +"&units=metric&appid=datafrom Openwether");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        System.out.println("result " + message + " "+result);

        while (in.hasNext()){
            result += in.nextLine();
            System.out.println("Result " + result);
        }


        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONObject wind = object.getJSONObject("wind");
        model.setWindSpead(wind.getDouble("speed"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject object1 = getArray.getJSONObject(i);
            model.setIcon((String) object1.get("icon"));
            model.setMain((String)object1.get("main"));
        }

        System.out.println("result end " + result);
        return "Місто " + model.getName() + "\n"
                + "Температура " + model.getTemp() + " C " + "\n" +
                "Вологість " + model.getHumidity() + " % " + "\n" +
                "Швидкість вітру " + model.getWindSpead() + " м/с " + "\n " +
                "Опис " + model.getMain() + " \n " +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";
    }
}
