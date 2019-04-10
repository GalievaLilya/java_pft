package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().contacts().size() == 0){
      app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
    }
    app.contact().returnToHomepage();
  }

  @Test
  public void contactDeletionTest() {
    if (! app.contact().isThereAContact()){
      app.goTo().AddNewContact();
      app.contact().create(new ContactData()
              .withFirstname("Boris").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
              .withHome("13").withMobile("1111111111").withGroup("test1"), true);
    }
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(),  before.size() - 1);
    assertThat(after, equalTo(before.withOut(deletedContact)));
    verifyContactListInUI();
  }
}
