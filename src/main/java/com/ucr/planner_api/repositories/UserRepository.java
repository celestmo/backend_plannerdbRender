package com.ucr.planner_api.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ucr.planner_api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    default User getById(String userId) {
        return findById(userId).orElse(null);
    }

    default User add(User user) {
        return save(user);
    }

    default User updateUser(User user) {
        return save(user);
    }
    
    @Query("SELECT u FROM User u WHERE u.resourceIdUser = :resourceId")
    Optional<User> findByResourceId(@Param("resourceId") UUID resourceId);

    Optional<User> findByResourceIdUser(UUID resourceIdUser);


    Boolean existsByUserName(String userName);

    boolean existsById(String userId);

}
