package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {

        super(wd);
    }
    private boolean acceptNextAlert = true;
    //private StringBuffer verificationErrors = new StringBuffer();

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstname());
        type(By.xpath("//input[@name='middlename']"), contactData.getMiddlename());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastname());
        type(By.xpath("//input[@name='nickname']"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());


        if (creation) {
            if (contactData.getGroup() != null){
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void saveContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void select(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }

    public void selectById(int id){
        wd.findElement(By.xpath("//input[@value='"+id+"']")).click();
    }

    public void delete(ContactData deletedContact) {
        selectById(deletedContact.getId());
        pushDelete();
        contactCache = null;
    }

    public void edit(ContactData modifiedContact, ContactData contact){
        wd.findElement(By.xpath("//input[@value='"+modifiedContact.getId()+"']/../..//img[@alt='Edit']")).click();
        fillContactData(contact, false);
        pushUpdate();
        contactCache = null;
    }

    public void pushUpdate(){
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void returnToHomepage() {
        click(By.linkText("home"));
    }

    public void pushDelete(){
        acceptNextAlert = true;
        click(By.xpath("//input[@value='Delete']"));
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

    public void create(ContactData contact, boolean creation){
        fillContactData(contact, creation);
        saveContact();
        contactCache = null;
        returnToHomepage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            String lastname = element.findElement(By.xpath("./td[2]")).getText();
            String firstname = element.findElement(By.xpath("./td[3]")).getText();
            String allAddress = element.findElement(By.xpath("./td[4]")).getText();
            String allEmails = element.findElement(By.xpath("./td[5]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id)
                    .withFirstname(firstname).withMiddlename(null).withLastname(lastname).withNickname(null).withCompany(null).withAddress(allAddress)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withGroup(null));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHome(home)
                .withMobile(mobile).withWork(work).withAddress(address).withEmail(email).withEmail2(email2)
                .withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.xpath("//tr[.//input[@value='"+id+"']]/td[8]/a")).click();
    }
}
