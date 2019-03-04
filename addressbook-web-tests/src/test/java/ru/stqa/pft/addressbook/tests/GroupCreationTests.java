package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation(){
    app.getNavigationHelper().gotoGroupPage();
   // int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test11"));
    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after,  before + 1);
    Assert.assertEquals(after.size(),  before.size() + 1);
    /*app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().filGroupForm(new GroupData("test1", "test2", "test11"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();*/
  }

}
