package com.crio.starterapp.service;
import java.util.List;
import com.crio.starterapp.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.crio.starterapp.repository.UserRepository;
@Service
public class LeaderboardService {
     private final UserRepository userRepository;

    @Autowired
    public LeaderboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User registerUser(String userId, String username) {
        User user = new User(userId, username);
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setScore(score);
        updateBadges(user);
        return userRepository.save(user);
    }

    private void updateBadges(User user) {
        user.getBadges().clear();
        if (user.getScore() >= 1 && user.getScore() < 30) {
            user.getBadges().add("Code Ninja");
        }
        if (user.getScore() >= 30 && user.getScore() < 60) {
            user.getBadges().add("Code Champ");
        }
        if (user.getScore() >= 60 && user.getScore() <= 100) {
            user.getBadges().add("Code Master");
        }
    }

    public List<User> getAllUsersSorted() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
