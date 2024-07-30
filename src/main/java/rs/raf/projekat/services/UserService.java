package rs.raf.projekat.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.projekat.entities.User;
import rs.raf.projekat.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);
        System.out.println(hashedPassword);
        User user = this.userRepository.findUser(email,hashedPassword);
        System.out.println(user);
        if (user != null && user.getStatus().equals("active")  ) {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            return JWT.create()
                    .withSubject(email)
                    .withClaim("id", user.getId())
                    .withClaim("userType", user.getUserType())
                    .withClaim("firstName", user.getFirstName())
                    .withClaim("lastName", user.getLastName())
                    .sign(algorithm);
        }

        return null;
    }

    public List<User> allUsers(){
        return this.userRepository.allUsers();
    }

    public User findUserById(int id){
        return this.userRepository.findUserById(id);
    }

    public User addUser(User user){
        return this.userRepository.addUser(user);
    }

    public User updateUserStatus(Integer id){
        return this.userRepository.updateUserStatus(id);
    }

    public User updateUser(User user, Integer id){
        return this.userRepository.updateUser(user, id);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        Integer id = jwt.getClaim("id").asInt();
        User user = this.userRepository.findUserById(id);

        if (user == null)
            return false;
         else if (user != null && user.getUserType().equals("admin"))
            return true;

        return true;
    }
}
