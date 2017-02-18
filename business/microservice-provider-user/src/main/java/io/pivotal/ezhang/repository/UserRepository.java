package io.pivotal.ezhang.repository;

/**
 * Created by ezhang on 2016/12/9.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.pivotal.ezhang.domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
