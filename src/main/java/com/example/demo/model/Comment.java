package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Long Usrid;
	private String Username;
	private Long ticketid;
	@Lob
	private String Body;
	private LocalDate Created;
	private LocalDate Edited;
	
	
	
	
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public Long getTicketid() {
		return this.ticketid;
	}

	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}


	public Comment() {
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUsrid() {
		return Usrid;
	}

	public void setUsrid(Long usrid) {
		Usrid = usrid;
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public LocalDate getCreated() {
		return Created;
	}

	public void setCreated(LocalDate created) {
		Created = created;
	}

	public LocalDate getEdited() {
		return Edited;
	}

	public void setEdited(LocalDate edited) {
		Edited = edited;
	}

	@Override
	public String toString() {
		return "Comment [Id=" + Id + ", Usrid=" + Usrid + ", Username=" + Username + ", ticketid=" + this.ticketid
				+ ", Body=" + Body + ", Created=" + Created + ", Edited=" + Edited + "]";
	}

	public Comment(Long id, Long usrid, String username, Long ticketid, String body, LocalDate created,
			LocalDate edited) {
		super();
		Id = id;
		Usrid = usrid;
		Username = username;
		this.ticketid = ticketid;
		Body = body;
		Created = created;
		Edited = edited;
	}

	


	

	

}
