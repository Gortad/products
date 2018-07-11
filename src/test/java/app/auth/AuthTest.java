package app.auth;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class AuthTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() throws Exception {
        userDetailsService.loadUserByUsername("user");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUserAfterInsert() throws Exception {
        userRepository.save(new User("user", bCryptPasswordEncoder.encode("12345")));
        userDetailsService.loadUserByUsername("user2");
    }


    @Test
    public void testUsers() throws Exception {
        userRepository.save(new User("user", bCryptPasswordEncoder.encode("12345")));
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        assertThat(userDetails != null);
        assertThat(userDetails.getUsername().equals("user"));
        assertThat(userDetails.getPassword().equals("12345"));
    }
}

