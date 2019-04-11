package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.LoginEmail;

public class ManageUserHelper extends HelperBase {

    public ManageUserHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public WebElement getUser() {
        return wd.findElement(By.xpath("(//a[contains(@href,'manage_user_edit_page.php?user_id') and not(text()='administrator')])[1]"));
    }

    public LoginEmail resetPassword() {
        String login = getUser().getText();
        getUser().click();
        return new LoginEmail(login,clickReset());
    }

    public String clickReset() {
        String email = wd.findElement(By.name("email")).getAttribute("value");
        click(By.xpath("//input[@value='Reset Password']"));
        return email;
    }
}
