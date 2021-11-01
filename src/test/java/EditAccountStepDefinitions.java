import org.openqa.selenium.By;

public class EditAccountStepDefinitions extends RunCucumberTest implements CommonMethods{
    @Override
    public void addByMap() {

        byMap.put("edit account lbl", By.xpath("//android.view.ViewGroup/android.widget.TextView"));

        byMap.put("pencil btn", By.xpath("//android.widget.ImageView[3]"));


        byMap.put("username edit error", By.xpath("//android.view.ViewGroup[1]/android.widget.LinearLayout[1]//android.widget.TextView"));
        byMap.put("save edit user", By.xpath("//android.widget.Button"));

        byMap.put("check box", By.xpath("//android.widget.CheckBox"));
        byMap.put("old password", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_old_password"));
        byMap.put("new password", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password"));
        byMap.put("confirm new password", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password_confirmation"));

        //byMap.put("old password error", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_old_password_Ti"));
        //byMap.put("new password error", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password_Ti"));
        //byMap.put("confirm new password error", By.id("com.ipleiria.estg.meicm.arcismarthome:id/et_password_confirmation_Ti"));

        byMap.put("old password error", By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.TextView[1]"));
        byMap.put("new password error", By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.TextView[1]"));
        byMap.put("confirm new password error", By.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.TextView[1]"));


    }

    @Override
    public void iSeeThatIAmInPage(String page) throws Exception {

    }

    @Override
    public void iClearAllInputsOfPage(String page) {
        iClearEditText("username edit text");
        iClearEditText("email edit text");
        iClearEditText("first name edit text");
        iClearEditText("last name edit text");
    }

    @Override
    public void iSeeAnErrorOnAllEditTextOfPage(String errorText, String pageName) throws Exception {

    }
}
