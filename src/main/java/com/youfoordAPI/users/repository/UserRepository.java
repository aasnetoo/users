package com.youfoordAPI.users.repository;

import com.youfoordAPI.users.entity.UserEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Mono<UserEntity> findById(String id);

    @Query("{ '_id': ?0 }, { 'status': 1 })")
    Mono<UserStatusProjection> findStatusById(String id);

    interface UserStatusProjection {
        UserEntity.Status getStatus();
    }
}
