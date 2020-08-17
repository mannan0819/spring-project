package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TicketGroup;

public interface TicketGrpRepo extends JpaRepository<TicketGroup, Long> {

}
