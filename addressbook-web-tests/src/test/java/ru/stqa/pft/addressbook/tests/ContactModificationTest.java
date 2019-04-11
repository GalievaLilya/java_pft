package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;


public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().contacts().size() == 0){
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
        }
        app.contact().returnToHomepage();

        if (! app.contact().isThereAContact()){
            app.goTo().AddNewContact();
            app.contact().create(new ContactData()
                    .withFirstname("Boris").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
                    .withHome("13").withMobile("1111111111"), true);
        }
    }

    @Test
    public void ContactModificationTest() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Boris3456").withMiddlename( "34567").withLastname("Tes").withNickname("Bib").withCompany("Ya").withAddress("Moscow")
                .withHome("13").withMobile("1111111111");
        app.contact().edit(modifiedContact, contact);
        app.goTo().HomePage();
        assertThat(app.contact().getContactCount(),  equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
