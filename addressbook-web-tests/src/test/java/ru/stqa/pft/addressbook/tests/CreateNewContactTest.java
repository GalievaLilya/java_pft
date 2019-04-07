package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateNewContactTest extends TestBase{

    @Test
    public void testCreateNewContact() {
        Contacts before = app.contact().all();
        app.goTo().AddNewContact();
        ContactData contact = new ContactData()
                .withFirstname("Boris1").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
                .withHome("13").withMobile("1111111111").withGroup("test1");
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(),  before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
