package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTaskTenTest extends TestBase {
    @Test
    public void contactTaskTenTest() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhone(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhone(ContactData contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(), contact.getWork())
                .stream().filter((s) -> !s.equals("")).map(ContactTaskTenTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
