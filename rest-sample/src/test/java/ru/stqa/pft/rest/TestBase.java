package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  public boolean isIssueOpen(int issueId) {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
    String statusOfIssue = issue.getAsJsonObject().get("state_name").toString();
    return !(statusOfIssue.equals("closed") || statusOfIssue.equals("resolved"));
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
