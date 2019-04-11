package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper( ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    }

    public void loginPage() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    }


}
