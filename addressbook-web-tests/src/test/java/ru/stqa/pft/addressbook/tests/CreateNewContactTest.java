package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateNewContactTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsXml() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));){
            String xml = "";
            String line = reader.readLine();
            while (line != null){
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream(new PureJavaReflectionProvider());
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));){
            String json = "";
            String line = reader.readLine();
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsJson")
    public void testCreateNewContact(ContactData contact) {
        Groups group = app.db().groups();
        contact.inGroup(group.iterator().next());
        Contacts before = app.db().contacts();
        app.goTo().AddNewContact();
       /* ContactData contact = new ContactData()
                .withFirstname("Boris1").withMiddlename( "Boris1").withLastname("Testing").withNickname("Bi").withCompany("Ya").withAddress("Moscow")
                .withHome("13").withMobile("1111111111").withGroup("test1");*/
        app.contact().create(contact, true);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(),  before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

}
