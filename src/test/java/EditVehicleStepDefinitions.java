import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

public class EditVehicleStepDefinitions extends RunCucumberTest implements CommonMethods {


    @Override
    public void addByMap() {
        // region vehicle
        byMap.put("empty vehicles", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_no_vehicles"));
        byMap.put("add vehicle", By.id("com.ipleiria.estg.meicm.arcismarthome:id/addVehicle"));

        byMap.put("vehicle icon", By.id("com.ipleiria.estg.meicm.arcismarthome:id/iv_car_icon"));
        byMap.put("brand", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_brand_data"));
        byMap.put("vehicle brand", By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]"));
        byMap.put("model", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_model_data"));
        byMap.put("vehicle model", By.xpath("//android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView[2]"));
        byMap.put("license plate", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_license_data"));
        byMap.put("vehicle license plate", By.xpath("//android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[4]"));
        byMap.put("year", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_year_data"));
        byMap.put("vehicle year", By.xpath("//android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.TextView[2]"));

        byMap.put("first vehicle", By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]"));

        byMap.put("create/edit vehicle title", By.id("com.ipleiria.estg.meicm.arcismarthome:id/tv_titleVehicle"));

        byMap.put("brand edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_brand"));
        byMap.put("brand error", By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout//android.widget.TextView"));
        byMap.put("model edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_model"));
        byMap.put("model error", By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));
        byMap.put("license plate edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_license"));
        byMap.put("license plate error", By.xpath("//android.widget.LinearLayout[3]//android.widget.TextView"));
        byMap.put("year edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_year"));
        byMap.put("year error", By.xpath("//android.widget.LinearLayout[4]//android.widget.TextView"));

        byMap.put("save", By.id("com.ipleiria.estg.meicm.arcismarthome:id/btn_edit_save"));
        byMap.put("delete", By.id("com.ipleiria.estg.meicm.arcismarthome:id/btn_delete"));
        // endregion
    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {
        super.iCheckTheEditTextHasA("Edit vehicle", "create/edit vehicle title");
        super.iSeeAnItem("save");
        super.iSeeAnItem("delete");
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

    }

    @And("I click in a vehicle")
    public void iClickInAVehicle() throws Exception {
        super.iClickButton("first vehicle");
    }
}
