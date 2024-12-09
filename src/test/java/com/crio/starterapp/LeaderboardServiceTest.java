package com.crio.starterapp;
import com.crio.starterapp.service.LeaderboardService;
import com.crio.starterapp.repository.UserRepository;
import com.crio.starterapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.List;

@SpringBootTest
public class LeaderboardServiceTest {
    @Autowired
    private LeaderboardService leaderboardService;

    @MockBean
    private UserRepository userRepository;

    @Test  
    public void testRegisterUser(){
       User user = new User("123", "john");
       when(userRepository.save(user)).thenReturn(user);
       User result = leaderboardService.registerUser("123", "john");
       assertEquals(user, result);
    }

    @Test
    public void testUpdateUserScore(){
        User user = new User("123", "john");
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User result = leaderboardService.updateUserScore("123", 100);
        assertEquals(user, result);
    }

    public void testGetAllUsersSorted(){
        User user1 = new User("123", "john");
        User user2 = new User("456", "jane");
        when(userRepository.findAllByOrderByScoreDesc()).thenReturn(List.of(user1, user2));
        List<User> result = leaderboardService.getAllUsersSorted();
        assertEquals(List.of(user1, user2), result);
    }  
}
