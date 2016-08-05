package fr.norsys.aop.fixture;

import fr.norsys.aop.domain.bean.User;

/**
 * Fixture pour les Users
 */
public class UserFixture {

    private final User user;

    public static UserFixture user() {
        return new UserFixture();
    }

    public static User user(final long id, final String name) {
        return user().withId(id).withName(name).build();
    }

    private UserFixture() {
        user = new User();
    }

    public UserFixture withId(final long id) {
        user.setId(id);
        return this;
    }

    public UserFixture withName(final String name) {
        user.setName(name);
        return this;
    }

    public User build() {
        return user;
    }

}
