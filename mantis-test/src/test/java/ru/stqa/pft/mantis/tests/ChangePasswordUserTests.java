package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordUserTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void registration() throws IOException, MessagingException {
    Users users = app.db().users();
    UserData user = users.iterator().next();
    if (user.getUsername().equals("administrator")) {
      user = users.iterator().next();
    }
    String password = "password1";
    app.login().login("administrator", "root");
    app.login().resetUserPasswordByAdmin(user.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 50000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.login().confirmChangePassword(confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user.getUsername(), password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
