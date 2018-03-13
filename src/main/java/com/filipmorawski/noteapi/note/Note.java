package com.filipmorawski.noteapi.note;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
class Note {
	
	Note() {
		this.title = "default title";
		this.content = "default content";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String title;
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date", insertable=true, updatable=false)
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private Date modifyDate;

	long getId() {
		return id;
	}

	void setId(long id) {
		this.id = id;
	}

	String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	Date getCreateDate() {
		return createDate;
	}

	void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	Date getModifyDate() {
		return modifyDate;
	}

	void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}	
	
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if(!(obj instanceof Note)) {
			return false;
		}
		Note note = (Note) obj;
		return id == note.id && 
				title == note.title &&
				content == note.content &&
				Objects.equals(createDate, note.createDate) &&
				Objects.equals(modifyDate, note.modifyDate);
	}
	public int hashCode() {
		return Objects.hash(id,title,content,createDate,modifyDate);
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
}
