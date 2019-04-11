package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AuthHelper extends HelperBase{

    public AuthHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void login(){
        app.navigationHelper().loginPage();
        type(By.name("username"),app.getProperty("web.adminLogin"));
        type(By.name("password"),app.getProperty("web.adminPassword"));
        click(By.xpath("//input[@value='Login']"));
    }
}
