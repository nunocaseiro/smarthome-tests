import org.openqa.selenium.By;

public class SettingsStepDefinitions extends RunCucumberTest implements CommonMethods{
    @Override
    public void addByMap() {
        byMap.put("Home Edit lbl", By.id("com.ipleiria.estg.meicm.arcismarthome:id/lb_home_edit"));
        byMap.put("Home Edit", By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText"));
        byMap.put("Latitude Edit", By.xpath("//android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText"));
        byMap.put("Longitude Edit", By.xpath("//android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"));
        byMap.put("Home Edit Error", By.xpath("//android.widget.LinearLayout/android.widget.LinearLayout//android.widget.TextView"));
        byMap.put("Latitude Edit Error", By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout//android.widget.TextView"));
        byMap.put("Longitude Edit Error", By.xpath("//android.widget.LinearLayout[3]/android.widget.LinearLayout//android.widget.TextView"));

        //settings
        byMap.put("fourth", By.id("com.ipleiria.estg.meicm.arcismarthome:id/navigation_settings"));
        byMap.put("SAVE1", By.xpath("//android.view.ViewGroup/android.widget.Button"));

    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {

    }

    @Override
    public void iClearAllInputsOfPage(String page) {
        iClearEditText("Home Edit");
        iClearEditText("Latitude Edit");
        iClearEditText("Longitude Edit");
    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {

    }
}
