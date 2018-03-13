package com.filipmorawski.noteapi.note;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class NoteDTO {

	@NotNull
	@Min(value=1L, message="Note has to got id")
	@ApiModelProperty(notes="Database generated note ID")
	private long id;

	@NotBlank(message="Note has to got name")
	@ApiModelProperty(notes="Note title")
	private String title;

	@NotBlank(message="Note has to got content")
	@ApiModelProperty(notes="Note content")
	private String content;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(notes="Database generated created time")
	private Date createDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(notes="Database generated last modification date")
	private Date modifyDate;

	public NoteDTO() {
	}
	
	public NoteDTO(long id, String title, String content, Date createDate, Date modifyDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(!(obj instanceof NoteDTO)) {
			return false;
		}
		NoteDTO dto = (NoteDTO) obj;
		return id==dto.id &&
				Objects.equals(title, dto.title) &&
				Objects.equals(content, dto.content) &&
				Objects.equals(createDate, dto.createDate) &&
				Objects.equals(modifyDate, dto.modifyDate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,title,content, createDate,modifyDate);
	}

	@Override
	public String toString() {
		return "NoteDTO [id=" + id + ", name=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]" + this.hashCode();
	}	
}
