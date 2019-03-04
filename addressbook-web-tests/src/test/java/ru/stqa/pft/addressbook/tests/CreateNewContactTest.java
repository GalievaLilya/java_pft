package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class CreateNewContactTest extends TestBase{

    @Test
    public void testCreateNewContact() {
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().createContact(new ContactData("Boris1", "Boris1", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111", "test1"), true);

        //int after = app.getContactHelper().getContactCount();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),  before.size() + 1);
        /*app.getContactHelper().fillContactData(new ContactData("Boris1", "Boris1", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111", "test1"), true);
        app.getContactHelper().saveContact();*/
    }

}
