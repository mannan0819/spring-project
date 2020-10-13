package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Comment;


public interface CommentRepo extends JpaRepository<Comment, Long> {

	public List<Comment> findAllByTicketid(Long id);
	
}
