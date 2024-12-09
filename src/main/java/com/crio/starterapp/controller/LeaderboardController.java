package com.crio.starterapp.controller;
import com.crio.starterapp.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crio.starterapp.service.LeaderboardService;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class LeaderboardController {
     private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @PostMapping
   public ResponseEntity<User> registerUser(@RequestBody User user) {
    User registeredUser = leaderboardService.registerUser(user.getUserId(), user.getUsername());
    return ResponseEntity.ok(registeredUser);
}

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = leaderboardService.getAllUsersSorted();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = leaderboardService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @RequestBody Map<String, Integer> requestBody) {
        Integer score = requestBody.get("score");
        if (score == null || score < 0 || score > 100) {
            return ResponseEntity.badRequest().build();
        }
        User user = leaderboardService.updateUserScore(userId, score);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        leaderboardService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
