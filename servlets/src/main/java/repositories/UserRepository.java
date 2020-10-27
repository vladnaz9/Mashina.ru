package repositories;

import models.User;

public interface UserRepository  extends CrudRepository<User> {
    User findByEmail(String email);
}
