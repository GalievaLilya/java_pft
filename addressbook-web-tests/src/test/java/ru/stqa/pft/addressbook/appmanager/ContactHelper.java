package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.testng.Assert.*;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {

        super(wd);
    }
    private boolean acceptNextAlert = true;
    //private StringBuffer verificationErrors = new StringBuffer();

    public void fillContactData(ContactData contactData) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstname());
        type(By.xpath("//input[@name='middlename']"), contactData.getMiddlename());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastname());
        type(By.xpath("//input[@name='nickname']"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
    }

    public void saveContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void selectContact(){
        click(By.name("selected[]"));
    }

    // xpath=(.//*[normalize-space(text()) and normalize-space(.)='Moscow'])[1]/following::img[2]
    public void editContact(){
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/preceding::img[2]"));
    }

    public void pushUpdate(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void pushDelete(){
        acceptNextAlert = true;
        click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]"));
        assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }
    private String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
