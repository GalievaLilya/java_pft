package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class CreateNewContactTest extends TestBase{

    @Test
    public void testCreateNewContact() throws Exception {
        app.getNavigationHelper().gotoAddNewContact();
        app.getGroupHelper().fillContactData(new ContactData("Boris", "Boris", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111"));
        app.saveContact();
    }

}
