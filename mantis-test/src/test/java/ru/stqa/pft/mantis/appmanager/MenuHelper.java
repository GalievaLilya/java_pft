package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class MenuHelper  extends HelperBase  {

    public MenuHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void logout() {
        click(By.xpath("//a[text()='Logout']"));
    }
}
