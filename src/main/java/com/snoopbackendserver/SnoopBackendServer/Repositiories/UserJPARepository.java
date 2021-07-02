package com.snoopbackendserver.SnoopBackendServer.Repositiories;

import com.snoopbackendserver.SnoopBackendServer.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJPARepository extends JpaRepository<User,UUID>{
    Optional<User> getUserByPhoneNumber(Long phoneNumber);
}
