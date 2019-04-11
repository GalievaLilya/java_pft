package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<>(users.delegate);
  }

  //строим множество из коллекции (копируем)
  public Users(Collection<UserData> users) {
    this.delegate = new HashSet<>(users);
  }


  public Users() {
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }

  public Users withAdded(UserData user){
    Users Users = new Users(this);
    Users.add(user);
    return Users;
  }

  public Users without(UserData user){
    Users Users = new Users(this);
    Users.remove(user);
    return Users;
  }
}

