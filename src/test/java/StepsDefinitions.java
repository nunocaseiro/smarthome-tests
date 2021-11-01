import io.cucumber.java.After;;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class StepsDefinitions extends RunCucumberTest implements CommonMethods {


    @Given("^I have the application successfully opened$")
    public void iHaveTheApplicationSuccessfullyOpened() throws MalformedURLException {
        houseCreated = false;
        userId = null;
        byMap.clear();
        addByMap();
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
        driver.resetApp();
    }

    public void addByMap() {
        // logo
        byMap.put("logo", By.id("com.ipleiria.estg.meicm.arcismarthome:id/logo"));

        // region login & register
        byMap.put("username edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_username"));
        byMap.put("username error", By.xpath("//android.view.ViewGroup[1]/android.widget.LinearLayout[1]//android.widget.TextView"));
        byMap.put("password edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password"));
        byMap.put("password error", By.xpath("//android.view.ViewGroup[2]/android.widget.LinearLayout[1]//android.widget.TextView"));

        byMap.put("login", By.id("com.ipleiria.estg.meicm.arcismarthome:id/btnLogin"));
        byMap.put("Want to create a new home", By.id("com.ipleiria.estg.meicm.arcismarthome:id/btnSignin"));
        byMap.put("submit", By.xpath("//android.widget.Button[@text = 'SUBMIT']"));
        // endregion

        // region dashboard
        byMap.put("house name", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_housename"));
        byMap.put("account name", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_firstname"));
        byMap.put("temperature text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/tv_temperature"));
        byMap.put("total sensors", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_total_s_a"));
        // endregion

        // region logout
        byMap.put("logout", By.id("com.ipleiria.estg.meicm.arcismarthome:id/iv_logout"));
        byMap.put("ok", By.id("android:id/button1"));
        byMap.put("no", By.id("android:id/button2"));
        byMap.put("yes", By.id("android:id/button1"));
        // endregion

        // region alert dialog
        byMap.put("dialog text", By.xpath("//android.widget.TextView"));
        byMap.put("ok dialog", By.id("android:id/button2"));

        //navigation
        byMap.put("vehicles navigation", By.id("com.ipleiria.estg.meicm.arcismarthome:id/navigation_vehicles"));
        byMap.put("settings navigation", By.id("com.ipleiria.estg.meicm.arcismarthome:id/navigation_settings"));
        // endregion

        // region others
        insertHomeKeyStepDefinitions.addByMap();
        registerHomeStepDefinitions.addByMap();
        createUserStepDefinitions.addByMap();
        createVehicleStepDefinitions.addByMap();
        editVehicleStepDefinitions.addByMap();
        settingsStepDefinitions.addByMap();
        editAccountStepDefinitions.addByMap();
        // endregion
    }

    @And("I see that I am in {string} page")
    public void iSeeThatIAmInPage(String page) throws Exception {
        sleep(500);
        switch (page) {
            case "login":
                iSeeAnItem("logo");
                iSeeAnItem("username edit text");
                iSeeAnItem("password edit text");
                iSeeAnItem("login");
                break;
            case "dashboard":
                iSeeAnItem("house name");
                iSeeAnItem("account name");
                iSeeAnItem("temperature text");
                iSeeAnItem("total sensors");
                break;
            case "vehicles":
                iSeeAnItem("house name");
                iSeeAnItem("add vehicle");
                break;
            case "settings":
                iSeeAnItem("Home Edit lbl");
                break;
            case "edit account":
                iSeeAnItem("edit account lbl");
                break;
            case "register home":
                registerHomeStepDefinitions.iSeeThatIAmInPage(page);
                break;
            case "create user":
                createUserStepDefinitions.iSeeThatIAmInPage(page);
                break;
            case "create vehicle":
                createVehicleStepDefinitions.iSeeThatIAmInPage(page);
                break;
            case "edit vehicle":
                editVehicleStepDefinitions.iSeeThatIAmInPage(page);
                break;
        }
    }

    @After
    public void tearDown() throws IOException, ParseException {
        //System.out.println(key != null);
      /*  if (key != null){
            deleteHouse(key);
            makeRequest("DELETE","http://161.35.8.148/api/housekeys/"+keyid+"/");
        }*/

        deleteHouse();

        if (userId != null) {
            deleteUser();
        }
        driver.quit();
    }

    @And("I click {string} button")
    @And("I press the {string} option in the navigation bar menu")
    public void iClickButton(String value) throws Exception {
        super.iClickButton(value);
    }

    @And("I see a {string}")
    public void iSeeAnItem(String value) throws Exception {
        super.iSeeAnItem(value);
    }


    @And("I type {string} on {string}")
    public void iTypeOnEditText(String string, String editTextName) throws Exception {
        super.iTypeOnEditText(string, editTextName);
    }


    @And("I check the {string} edit text has {string}")
    public void iCheckTheEditTextHas(String editTextName, String string) throws Exception {
        super.iCheckTheEditTextHasA(string, editTextName);
    }

    @When("I clear {string}")
    public void iClearEditText(String editTextName) {
        super.iClearEditText(editTextName);
    }

    @And("I check {string} is clear")
    public void iCheckEditTextIsClear(String editTextName) throws Exception {
        super.iCheckEditTextIsClear(editTextName);
    }

    @Then("I see an error {string} on {string} edit text")
    public void iSeeAnErrorOnEditText(String string, String errorEditText) throws Exception {
        super.iSeeAnErrorOnEditText(string, errorEditText);
    }

    @Then("I see an alert dialog saying {string}")
    public void iSeeAnAlertDialogSaying(String text) throws Exception {
        super.iSeeAnAlertDialogSaying(text);
    }

    @When("I clear all inputs of {string} page")
    public void iClearAllInputsOfPage(String page) {
        switch (page) {
            case "register home":
                registerHomeStepDefinitions.iClearAllInputsOfPage(page);
                break;
            case "create user":
                createUserStepDefinitions.iClearAllInputsOfPage(page);
                break;
            case "create vehicle":
                createVehicleStepDefinitions.iClearAllInputsOfPage(page);
                break;
            case "edit vehicle":
                editVehicleStepDefinitions.iClearAllInputsOfPage(page);
                break;
            case "settings":
                settingsStepDefinitions.iClearAllInputsOfPage(page);
                break;
            case "edit user":
                editAccountStepDefinitions.iClearAllInputsOfPage(page);
                break;
        }
    }

    @Then("I see an error {string} on all edit text of {string} page")
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {
        switch (pageName) {
            case "register home":
                registerHomeStepDefinitions.iSeeAnErrorOnAllEditTextOfPage(errorText, pageName);
                break;
            case "create user":
                createUserStepDefinitions.iSeeAnErrorOnAllEditTextOfPage(errorText, pageName);
                break;
            case "create vehicle":
                createVehicleStepDefinitions.iSeeAnErrorOnAllEditTextOfPage(errorText, pageName);
                break;
        }
    }

    @And("I {string} {string} on {string}")
    public void iDoSomethingOn(String action, String text, String editText) throws Exception {
        if (action.equals("type")) {
            iTypeOnEditText(text, editText);
        }
        if (action.equals("clear")) {
            iClearEditText(editText);
        }
    }


    @Then("I {string} an error {string} on {string} edit text")
    public void iAnErrorOnEditText(String action, String errorText, String editText) throws Exception {
        if (action.equals("see")) {
            iSeeAnErrorOnEditText(errorText, editText);
        }

        if (action.equals("don't see")) {
            iDontSeeSomething(editText);
        }
    }

    @And("I ask to server if a {string} with inserted name was created")
    public void iAskToServerIfAWithNameWasCreated(String object) {
        switch (object) {
            case "user":
                super.iAskToServerIfAnUserWithUsernameWasCreated();
                break;
            case "home":
                super.iAskToServerIfAnHomeWithNameWasCreated();
                break;
        }
    }

    @And("I do logout")
    public void iDoLogout() throws Exception {
        iClickButton("settings navigation");
        iClickButton("logout");
        iClickButton("yes");
        iSeeThatIAmInPage("login");
    }

    @And("I press back")
    public void iPressBack() throws Exception {
        super.iPressBack();
    }

    @And("I am at the {string} page")
    public void iAmAtThePage(String page) throws Exception {
        super.createHomeAndUser();
        this.iSeeThatIAmInPage("login");
        this.iTypeOnEditText(username, "username edit text");
        this.iTypeOnEditText(password, "password edit text");
        this.iClickButton("login");
        this.iSeeThatIAmInPage("dashboard");

        switch (page) {
            case "vehicle":
                this.makeRequest("GET", "http://161.35.8.148/api/deleteallvehicles?houseKey=58", null);
                this.iClickButton("vehicles navigation");
                this.iSeeThatIAmInPage("vehicle");
                break;
            case "settings":
                this.iClickButton("fourth");
                this.iSeeThatIAmInPage("settings");
                break;
            case "edit user":
                this.iSeeThatIAmInPage("edit account");
                break;

        }

    }


    @And("I {string} a vehicle at the top of the list")
    @And("I confirm that the vehicle has been {string}")
    public void iSeeAVehicleAtTheTopOfTheList(String action) throws Exception {
        switch (action) {
            case "updated":
                super.iCheckTheEditTextHasA("Porsche", "brand");
                super.iCheckTheEditTextHasA("Panamera", "model");
                super.iCheckTheEditTextHasA("88XX88", "license plate");
                super.iCheckTheEditTextHasA("2018", "year");
                break;
            case "deleted":
                super.iSeeAnItem("empty vehicles");
                break;
            default:
                super.iCheckTheEditTextHasA("Tesla", "brand");
                super.iCheckTheEditTextHasA("S", "model");
                super.iCheckTheEditTextHasA("99ZZ99", "license plate");
                super.iCheckTheEditTextHasA("2019", "year");
                break;
        }
    }

    @And("I do login with new credentials, {string}, {string}")
    public void iDoLoginWithNewCredentials(String user,String pw) throws Exception {
        this.iTypeOnEditText(user, "username edit text");
        this.iTypeOnEditText(pw, "password edit text");
        this.iClickButton("login");
        this.iSeeThatIAmInPage("dashboard");
    }
}