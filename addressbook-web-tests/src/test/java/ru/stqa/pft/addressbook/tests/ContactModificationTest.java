package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class ContactModificationTest extends TestBase{

    @Test
    public void ContactModificationTest(){
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test1", "test2"));
        }


        app.getContactHelper().returnToHomepage();

        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoAddNewContact();
            app.getContactHelper().createContact(new ContactData("Boris", "Boris1", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111", "test1"), true);
        }
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactData(new ContactData("Boris3456", "34567", "Tes", "Bib", "Ya", "Moscow", "13", "1111111111", null), false);
        app.getContactHelper().pushUpdate();
        app.getNavigationHelper().gotoHomePage();
        //int after = app.getContactHelper().getContactCount();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),  before.size());

    }
}
