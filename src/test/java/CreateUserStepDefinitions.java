import io.cucumber.java.en.And;
import org.openqa.selenium.By;

public class CreateUserStepDefinitions extends RunCucumberTest implements CommonMethods {

    public CreateUserStepDefinitions() {
    }

    @And("I create an admin user")
    public void iCreateAnAdminUser() throws Exception {
        iSeeThatIAmInPage("create user");
        iTypeOnEditText(username,"username edit text");
        iTypeOnEditText("smarthomeqs@gmail.com","email edit text");
        iTypeOnEditText("Qualidade","first name edit text");
        iTypeOnEditText("Casa QS","last name edit text");
        iTypeOnEditText(password,"password edit text");
        iTypeOnEditText(password,"password confirmation edit text");
        iClickButton("create account");
    }

    public void addByMap(){
        //create user
        byMap.put("create user title", By.id("com.ipleiria.estg.meicm.arcismarthome:id/tv_title_account"));

        byMap.put("email edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_email"));
        byMap.put("email edit error", By.xpath("//android.view.ViewGroup[1]/android.widget.LinearLayout[2]//android.widget.TextView"));

        byMap.put("first name edit text",By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_first_name"));
        byMap.put("first name error",By.xpath("//android.widget.LinearLayout[3]//android.widget.TextView"));

        byMap.put("last name edit text",By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_last_name"));
        byMap.put("last name error",By.xpath("//android.widget.LinearLayout[4]//android.widget.TextView"));

        byMap.put("password confirmation edit text",By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password_confirmation"));
        byMap.put("password confirmation error",By.xpath("//android.view.ViewGroup[2]/android.widget.LinearLayout[2]//android.widget.TextView"));

        byMap.put("create account",By.xpath("//android.widget.Button[@text = 'CREATE']"));
    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {
        if (page.equals("create user")){
            iSeeAnItem("create user title");
            iCheckTheEditTextHasA("Create account","create user title");
            iSeeAnItem("username edit text");
            iSeeAnItem("password edit text");
            iSeeAnItem("email edit text");
            iSeeAnItem("password confirmation edit text");
        }
    }

    @Override
    public void iClearAllInputsOfPage(String page) {
        iClearEditText("username edit text");
        iClearEditText("password edit text");
        iClearEditText("email edit text");
        iClearEditText("first name edit text");
        iClearEditText("last name edit text");
        iClearEditText("password confirmation edit text");
    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {
        iSeeAnErrorOnEditText(errorText,"username error");
        iSeeAnErrorOnEditText(errorText,"password error");
        iSeeAnErrorOnEditText(errorText,"email edit error");
        iSeeAnErrorOnEditText(errorText,"first name error");
        iSeeAnErrorOnEditText(errorText,"last name error");
        iSeeAnErrorOnEditText(errorText,"password confirmation error");
    }


}
