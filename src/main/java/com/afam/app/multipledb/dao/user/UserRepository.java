package com.afam.app.multipledb.dao.user;

import com.afam.app.multipledb.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
