import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;


public class RegisterHomeStepDefinitions extends RunCucumberTest implements CommonMethods{

    public RegisterHomeStepDefinitions() {

    }

    public void addByMap(){
        //register home edittext
        byMap.put("house name edit text",By.xpath("//android.widget.EditText[1]"));
        byMap.put("house name error",By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout//android.widget.TextView"));

        byMap.put("latitude edit text",By.id("com.ipleiria.estg.meicm.arcismarthome:id/etLatitude"));
        byMap.put("latitude error", By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));

        byMap.put("longitude edit text",By.id("com.ipleiria.estg.meicm.arcismarthome:id/etLongitude"));
        byMap.put("longitude error",By.xpath("//android.widget.LinearLayout[3]//android.widget.TextView"));

        byMap.put("create house" ,By.xpath("//android.widget.Button[@text = 'CREATE']"));
    }



    @When("I insert a valid key")
    public void iInsertAValidKey() throws Exception {
      /* if (key == null){
           String response = makeRequest("GET","http://161.35.8.148/api/createkey/");
           JSONParser parser = new JSONParser();
           JSONObject responseObject = (JSONObject) parser.parse(response);
           key =  responseObject.get("key").toString();
           keyid = responseObject.get("id").toString();
       }*/
        iTypeOnEditText(houseKey,"key edit text");
    }


    public void iSeeThatIAmInPage(String page) throws Exception {
        if(page.equals("register home")){
            iSeeAnItem("house name edit text");
            iSeeAnItem("latitude edit text");
            iSeeAnItem("longitude edit text");
            iSeeAnItem("create house");
        }
    }

    @Override
    public void iClearAllInputsOfPage(String page){
        iClearEditText("house name edit text");
        iClearEditText("latitude edit text");
        iClearEditText("longitude edit text");
    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {
        iSeeAnErrorOnEditText(errorText,"house name error");
        iSeeAnErrorOnEditText(errorText,"latitude error");
        iSeeAnErrorOnEditText(errorText,"longitude error");
    }

    @And("I create a house")
    public void iCreateAHouse() throws Exception {
        houseCreated = true;
        iSeeThatIAmInPage("register home");
        iTypeOnEditText("SoftQuality","house name edit text");
        iTypeOnEditText("39.73","latitude edit text");
        iTypeOnEditText("-8.82","longitude edit text");
        iClickButton("create house");
    }
}
