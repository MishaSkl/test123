package ru.kataproject.p_sm_airlines_1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataproject.p_sm_airlines_1.entity.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String name);
}
