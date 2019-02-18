package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class CreateNewContactTest extends TestBase{

    @Test
    public void testCreateNewContact() {
        app.getNavigationHelper().gotoAddNewContact();
        app.getContactHelper().fillContactData(new ContactData("Boris1", "Boris1", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111"));
        app.getContactHelper().saveContact();
    }

}
