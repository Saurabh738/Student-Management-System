package com.app.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.app.model.Admin;



@Repository
public interface AdminProfileDao extends JpaRepository<Admin, Integer> {

	Admin findByadminId(int adminId);

}
