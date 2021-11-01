import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.cucumber.testng.*;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@CucumberOptions(features = "src/test/resources", plugin = {"pretty"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    protected static String usernameBS;
    protected static String accessKey;
    protected static String app;
    protected static DesiredCapabilities capabilities;
    protected static JSONObject config;
    protected static AndroidDriver driver;

    protected static By by;
    protected static HashMap<String, By> byMap = new HashMap();
    protected static String houseKey = "7994398087";
    protected static String homeId = "";
    protected static String username = "smarthomeqs";
    protected static String password = "meicm123";
    protected static String userId = "";

    boolean houseCreated = false;

    protected static StepsDefinitions stepsDefinitions;
    protected static RegisterHomeStepDefinitions registerHomeStepDefinitions;
    protected static CreateUserStepDefinitions createUserStepDefinitions;
    protected static InsertHomeKeyStepDefinitions insertHomeKeyStepDefinitions;
    protected static CreateVehicleStepDefinitions createVehicleStepDefinitions;
    protected static EditVehicleStepDefinitions editVehicleStepDefinitions;
    protected static SettingsStepDefinitions settingsStepDefinitions;
    protected static EditAccountStepDefinitions editAccountStepDefinitions;


    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"deviceIndex", "remote"})
    public void setDriver(String deviceIndex, String remote) throws IOException, ParseException {
        stepsDefinitions = new StepsDefinitions();
        insertHomeKeyStepDefinitions = new InsertHomeKeyStepDefinitions();
        registerHomeStepDefinitions = new RegisterHomeStepDefinitions();
        createUserStepDefinitions = new CreateUserStepDefinitions();
        createVehicleStepDefinitions = new CreateVehicleStepDefinitions();
        editVehicleStepDefinitions = new EditVehicleStepDefinitions();

        settingsStepDefinitions = new SettingsStepDefinitions();
        editAccountStepDefinitions = new EditAccountStepDefinitions();
        
        if (remote.equals("true"))
            setUpRemote(deviceIndex);
        else
            setUpLocal();
    }

    public void setUpLocal() throws MalformedURLException {
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), getCapabilities());
    }

    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        //capabilities.setCapability(MobileCapabilityType.UDID, "L2N4C19926018730");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ipleiria.estg.meicm.arcismarthome");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ipleiria.estg.meicm.arcismarthome.LoginActivity");
        return capabilities;
    }

    public void setUpRemote(String deviceIndex) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        config = (JSONObject) parser.parse(new FileReader("src/test/resources/config/browserstack.conf.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        usernameBS = System.getenv("BROWSERSTACK_USERNAME");
        if (usernameBS == null) {
            usernameBS = (String) config.get("username");
        }

        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("access_key");
        }

        app = System.getenv("BROWSERSTACK_APP_ID");
        if (app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        this.driver = new AndroidDriver(new URL("http://" + usernameBS + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
    }


    public String makeRequest(String method, String url, RequestBody body) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic c21hcnRob21lOm1laWNtMTIz")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void sleep(int milliseconds) throws Exception {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            throw new Exception("Pause between steps was interrupted", e);
        }
    }

    public void deleteHouse() {
        try {
            String response = makeRequest("GET", "http://161.35.8.148/api/gethousewkey?key=" + houseKey, null);
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(response);
            homeId = responseObject.get("id").toString();
            makeRequest("DELETE", "http://161.35.8.148/api/homes/" + homeId + "/", null);
        } catch (Exception e) {

        }
    }

    public void deleteUser() {
        try {
            makeRequest("GET", "http://161.35.8.148/api/deleteuserandprofile?id=" + userId, null);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public void iClickButton(String value) throws Exception {
        sleep(1000);
        by = byMap.get(value);
        driver.findElement(by).click();
    }

    public void iSeeAnItem(String value) throws Exception {
        sleep(700);
        by = byMap.get(value);
        Assert.assertTrue(driver.findElement(by).isDisplayed());
    }

    public void iTypeOnEditText(String string, String editTextName) throws Exception {
        sleep(700);
        by = byMap.get(editTextName);
        driver.findElement(by).sendKeys(string);
    }

    public void iCheckTheEditTextHasA(String string, String editTextName) throws Exception {
        sleep(700);
        by = byMap.get(editTextName);
        Assert.assertTrue(driver.findElement(by).getText().contains(string));
    }

    public void iClearEditText(String editTextName) {
        by = byMap.get(editTextName);
        driver.findElement(by).clear();
    }

    public void iCheckEditTextIsClear(String editTextName) throws Exception {
        sleep(700);
        by = byMap.get(editTextName);
        driver.findElement(by).getText().isEmpty();
    }

    public void iSeeAnErrorOnEditText(String string, String errorEditText) throws Exception {
        sleep(700);
        by = byMap.get(errorEditText);
        Assert.assertTrue(driver.findElement(by).getText().contains(string));
    }

    protected void iSeeAnAlertDialogSaying(String text) throws Exception {
        sleep(700);
        by = byMap.get("dialog text");
        Assert.assertTrue(driver.findElement(by).getText().contains(text));
    }

    protected void iPressBack() throws Exception {
        sleep(1000);
        driver.pressKey(new KeyEvent(Arrays.stream(AndroidKey.values()).filter(k -> k.getCode() == 4).findFirst().get()));
    }

    public void iDontSeeSomething(String object) {
        by = byMap.get(object);
        try {
            WebElement e = driver.findElement(by);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    protected void iAskToServerIfAnUserWithUsernameWasCreated() {
        try {
            String response = makeRequest("GET", "http://161.35.8.148/api/getuserid?username=" + username, null);
            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(response);
            userId = responseObject.get("id").toString();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    protected void iAskToServerIfAnHomeWithNameWasCreated() {
        try {
            houseCreated = true;
            //String response = makeRequest("GET", "http://161.35.8.148/api/gethousewkey?key=" + key);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    protected void createHomeAndUser() {
        try {
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("name", "Casa QS")
                    .addFormDataPart("key", houseKey)
                    .addFormDataPart("latitude", "64.1")
                    .addFormDataPart("longitude", "-5.12")
                    .build();
            String response = makeRequest("POST", "http://161.35.8.148/api/homes/", body);

            JSONParser parser = new JSONParser();
            JSONObject responseObject = (JSONObject) parser.parse(response);
            homeId = responseObject.get("id").toString();

            body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("username", username)
                    .addFormDataPart("email", "smarthomeqs@gmail.com")
                    .addFormDataPart("first_name", "Qualidade")
                    .addFormDataPart("last_name", "Casa QS")
                    .addFormDataPart("groups", "admin")
                    .addFormDataPart("password", password)
                    .addFormDataPart("password2", password)
                    .addFormDataPart("home", homeId)
                    .build();
            makeRequest("POST", "http://161.35.8.148/api/register/", body);

            response = makeRequest("GET", "http://161.35.8.148/api/getuserid?username=" + username, null);
            parser = new JSONParser();
            responseObject = (JSONObject) parser.parse(response);
            userId = responseObject.get("id").toString();
        } catch (Exception e) {
            Assert.fail();
        }
    }
}


