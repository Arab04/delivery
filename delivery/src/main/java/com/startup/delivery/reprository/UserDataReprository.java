package com.startup.delivery.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.startup.delivery.modul.UserData;

@Repository
public interface UserDataReprository extends JpaRepository<UserData, Long>{

}
