package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ticket_group")
public class TicketGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Name;
	private String Author;
	private LocalDate Created;
	private String Description;
	
	public TicketGroup() {
	}
	
	public TicketGroup(Long id, String name, String author, LocalDate created, String description) {
		Id = id;
		Name = name;
		Author = author;
		Created = created;
		Description = description;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public LocalDate getCreated() {
		return Created;
	}
	public void setCreated(LocalDate created) {
		Created = created;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	

}
