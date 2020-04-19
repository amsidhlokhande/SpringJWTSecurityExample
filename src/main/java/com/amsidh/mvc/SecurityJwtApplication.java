package com.amsidh.mvc;

import com.amsidh.mvc.auth.model.EnumRole;
import com.amsidh.mvc.auth.model.Role;
import com.amsidh.mvc.repository.RoleRepository;
import com.amsidh.mvc.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
@AllArgsConstructor
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, RoleRepository.class})
public class SecurityJwtApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
         roleRepository.saveAll(Arrays.asList(
                 new Role(EnumRole.ROLE_USER),
                 new Role(EnumRole.ROLE_ADMIN),
                 new Role(EnumRole.ROLE_MODERATOR)));
    }
}
