package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void ContactModificationTest(){
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactData(new ContactData("Boris", "Boris", "Tes", "Bib", "Ya", "Moscow", "13", "1111111111"));
        app.getContactHelper().pushUpdate();
        app.getNavigationHelper().gotoHomePage();

    }
}
