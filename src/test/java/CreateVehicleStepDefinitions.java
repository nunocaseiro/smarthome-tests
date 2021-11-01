import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

public class CreateVehicleStepDefinitions extends RunCucumberTest implements CommonMethods {

    @Override
    public void addByMap() {
    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {
        super.iCheckTheEditTextHasA("Create vehicle", "create/edit vehicle title");
        super.iSeeAnItem("save");
        super.iDontSeeSomething("delete");
    }

    @Override
    public void iClearAllInputsOfPage(String page) {
        iClearEditText("brand edit text");
        iClearEditText("model edit text");
        iClearEditText("license plate edit text");
        iClearEditText("year edit text");
    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {
        iSeeAnErrorOnEditText(errorText, "brand error");
        iSeeAnErrorOnEditText(errorText, "model error");
        iSeeAnErrorOnEditText(errorText, "license plate error");
        iSeeAnErrorOnEditText(errorText, "year error");
    }

    @And("I create a vehicle")
    public void iCreateAVehicle() throws Exception {
        super.iClickButton("add vehicle");
        this.iSeeThatIAmInPage("create vehicle");
        super.iTypeOnEditText("Tesla", "brand edit text");
        super.iTypeOnEditText("S", "model edit text");
        super.iTypeOnEditText("99ZZ99", "license plate edit text");
        super.iTypeOnEditText("2019", "year edit text");
        super.iClickButton("save");
        super.iClickButton("ok dialog");
        stepsDefinitions.iSeeThatIAmInPage("vehicle");
    }
}
