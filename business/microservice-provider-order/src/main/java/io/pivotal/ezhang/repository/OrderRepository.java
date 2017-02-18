package io.pivotal.ezhang.repository;

/**
 * Created by ezhang on 2016/12/9.
 */
import io.pivotal.ezhang.domain.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}
