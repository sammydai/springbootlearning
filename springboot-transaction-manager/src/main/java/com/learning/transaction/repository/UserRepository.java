package com.learning.transaction.repository;

import com.learning.transaction.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.learning.transaction.repository
 * @Description: User Repository 整合JPA数据源的操作
 * @Author: Sammy
 * @Date: 2020/12/2 09:53
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

	List<UserEntity> findByName(String name);

}
