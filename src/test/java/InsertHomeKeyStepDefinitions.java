import org.openqa.selenium.By;

public class InsertHomeKeyStepDefinitions extends RunCucumberTest implements CommonMethods{
    @Override
    public void addByMap() {
        byMap.put("key edit text", By.id("com.ipleiria.estg.meicm.arcismarthome:id/etHouseKey"));
        byMap.put("key error",By.id("com.ipleiria.estg.meicm.arcismarthome:id/textinput_error"));
    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {

    }

    @Override
    public void iClearAllInputsOfPage(String page) {

    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) {

    }
}
