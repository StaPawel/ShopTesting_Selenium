package shopTestPack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SetupJson {
    JSONParser jsonParser = new JSONParser();
    JSONObject setup;

    SetupJson() {
        //Zastanowić sie czy dane umiescic w jednym pliku
        //czy podzielic je na dwie czesci -> setup.json i testData.json
        try (FileReader reader = new FileReader("setup.json")) {
            Object object = jsonParser.parse(reader);
            setup = (JSONObject) object;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }catch (ParseException e){
        }
    }
    String getDriverPath (){
        return (String)setup.get("driverPath");
    }
    String getUrlPath (){
        return (String)setup.get("urlPath");
    }
    String getWebDriver (){
        return (String)setup.get("webDriver");
    }
}
