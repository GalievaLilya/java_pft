package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {

        super(wd);
    }
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


}
