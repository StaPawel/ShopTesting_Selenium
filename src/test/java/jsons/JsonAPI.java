package jsons;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonAPI {
    JSONParser jsonParser = new JSONParser();
    JSONObject getDataFromJSON;

    public JsonAPI (String jsonFileName){
        try (FileReader reader = new FileReader("src\\test\\java\\jsons\\" + jsonFileName)){
            Object object = jsonParser.parse(reader);
            getDataFromJSON = (JSONObject) object;
        }catch (FileNotFoundException e) {
        } catch (IOException e) {
        }catch (ParseException e){
        }
    }

    public ArrayList <String>  getInvalidEmailAddress(){

        JSONArray jsonArray = (JSONArray) getDataFromJSON.get("invalidEmailAddress");
        ArrayList <String> list = new ArrayList<String>();

        for(Object o : jsonArray){
            list.add(o.toString());
        }
        return list;
    }

    public String getFromJSON(String jsonString){
        return (String) getDataFromJSON.get(jsonString);
    }
}
