package ru.stqa.pft.addressbook;

import org.testng.annotations.*;



public class CreateNewContact extends TestBase{

    @Test
    public void testCreateNewContact() throws Exception {
        gotoAddNewContact();
        fillContactData(new ContactData("Boris", "Boris", "Testing", "Bi", "Ya", "Moscow", "13", "1111111111"));
        saveContact();
    }

}
