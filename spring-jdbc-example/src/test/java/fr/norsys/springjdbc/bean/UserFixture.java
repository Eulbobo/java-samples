package fr.norsys.springjdbc.bean;

import java.util.ArrayList;
import java.util.List;

import fr.norsys.springjdbc.beans.User;

public class UserFixture {

    private final User fixture = new User();

    public static UserFixture user() {
        return new UserFixture();
    }

    public UserFixture withId(final int id) {
        fixture.setId(id);
        return this;
    }

    public UserFixture withName(final String name) {
        fixture.setName(name);
        return this;
    }

    public UserFixture withMail(final String mail) {
        fixture.setMail(mail);
        return this;
    }

    public User build() {
        return fixture;
    }

    public static User first() {
        return user().withId(1).withMail("julien@norsys.fr").withName("Julien").build();
    }

    public static User second() {
        return user().withId(2).withMail("alys@norsys.fr").withName("Alys").build();
    }

    public static User third() {
        return user().withId(3).withMail("didier@norsys.fr").withName("Didier").build();
    }

    public static List<User> all() {
        List<User> all = new ArrayList<User>();
        all.add(first());
        all.add(second());
        all.add(third());
        return all;
    }

    public static User[] allArray() {
        return all().toArray(new User[] {});
    }
}
