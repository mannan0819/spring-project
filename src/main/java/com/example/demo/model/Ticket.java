package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String Title;
	private String Author;
	private LocalDate Created;
	private LocalDate Edited;
    private String Status;
    private String Priority;
    @Lob
    private String Description;
    private Long groupid;
    private String Filename;
    private boolean Isimg;
    
    
    public Ticket() {
    	
    }

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
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

	public LocalDate getEdited() {
		return Edited;
	}

	public void setEdited(LocalDate edited) {
		Edited = edited;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public boolean getIsimg() {
		return Isimg;
	}

	public void setIsimg(boolean isImg) {
		this.Isimg = isImg;
	}

	public Ticket(String title, String author, LocalDate created, LocalDate edited, String status, String priority,
			String description, Long groupid, String filename, boolean isImg) {
		super();
		Title = title;
		Author = author;
		Created = created;
		Edited = edited;
		Status = status;
		Priority = priority;
		Description = description;
		groupid = groupid;
		Filename = filename;
		this.Isimg = isImg;
	}

	@Override
	public String toString() {
		return "Ticket [Id=" + Id + ", Title=" + Title + ", Author=" + Author + ", Created=" + Created + ", Edited="
				+ Edited + ", Status=" + Status + ", Priority=" + Priority + ", Description=" + Description
				+ ", Groupid=" + groupid + ", Filename=" + Filename + ", isImg=" + Isimg + "]";
	}
    
	
    
}
