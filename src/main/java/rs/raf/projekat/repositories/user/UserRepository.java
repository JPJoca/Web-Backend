package rs.raf.projekat.repositories.user;

import rs.raf.projekat.entities.User;

import java.util.List;

public interface UserRepository {
    public List<User> allUsers();
    public User findUser(String email, String password);
    public User findUserById(int id);
    public User addUser(User user);
    public User updateUserStatus(Integer id);
    public User updateUser(User user, Integer id);
}
