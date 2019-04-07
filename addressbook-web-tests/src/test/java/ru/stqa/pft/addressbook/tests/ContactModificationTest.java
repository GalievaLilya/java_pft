package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTest extends TestBase{

    @Test
    public void ContactModificationTest() throws InterruptedException {
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
        app.getContactHelper().editContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Boris3456", "34567", "Tes", "Bib", "Ya", "Moscow", "13", "1111111111", null);
        app.getContactHelper().fillContactData(contact, false);
        app.getContactHelper().pushUpdate();
        app.getNavigationHelper().gotoHomePage();
        //int after = app.getContactHelper().getContactCount();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),  before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
