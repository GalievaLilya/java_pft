package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

  @Test
  public void contactDeletionTest() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test1", "test2"));
    }

    app.getContactHelper().returnToHomepage();

    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewContact();
      app.getContactHelper().createContact(new ContactData("Boris", "Boris1", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111", "test1"), true);
    }
   // int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().pushDelete();
    app.getNavigationHelper().gotoHomePage();
    //int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),  before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }
}
