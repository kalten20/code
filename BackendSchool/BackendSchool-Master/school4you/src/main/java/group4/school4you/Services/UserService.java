package group4.school4you.Services;

import group4.school4you.Entities.User;
import group4.school4you.Repositories.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserJpaRepository userRepository;

    public List<User> findAll(){
        List<User> all = userRepository.findAll();
        return all;
    }

    public User findById(long id){
        return userRepository.findById(id).get();
    }
}
