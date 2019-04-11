package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


public class DeletionContactFromGroups extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData group = new GroupData().withName("Test1");
        ContactData contact = new ContactData().withFirstname("Boris2").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
                .withHome("13").withMobile("1111111111");

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(group);
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) app.contact().create(contact.inGroup(groups.iterator().next()), true);
    }

    @Test
    public void delContactFromGroup() {

        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactToAdd = contacts.iterator().next();
        GroupData groupForContactToAdd = groups.iterator().next();
        app.goTo().HomePage();

        if (contactToAdd.getGroups().size() == 0) {
            app.contact().addContactToGroup(contactToAdd, groupForContactToAdd);
            app.goTo().HomePage();
        }
        contacts = app.db().contacts();
        Groups groupsToDeleteContact = contacts.iterator().next().getGroups();
        app.contact().deleteContactFromGroup(contactToAdd, groupsToDeleteContact);

    }
}