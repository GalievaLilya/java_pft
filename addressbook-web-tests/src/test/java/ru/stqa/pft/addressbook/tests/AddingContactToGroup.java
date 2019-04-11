package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("Test1");
        ContactData contact = new ContactData().withFirstname("Boris1").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
                .withHome("13").withMobile("1111111111");

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(group);
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) app.contact().create(contact.inGroup(groups.iterator().next()), true);
    }

    @Test (enabled = true)
    public void testContactDeletionFromGroup() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactToAdd = contacts.iterator().next();
        GroupData groupForAddingContact = groups.iterator().next();
        Groups groupsForAddingContact = contacts.iterator().next().getGroups();
        app.goTo().HomePage();
        app.contact().addContactToGroup(contactToAdd,groupForAddingContact);
        Contacts contactsAfter = app.db().contacts();
        Groups groupsToAddContactAfter = contactsAfter.iterator().next().getGroups();

        assertThat(groupsToAddContactAfter,
                equalTo(groupsForAddingContact.withAdded(groupForAddingContact.withId(groupsToAddContactAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}