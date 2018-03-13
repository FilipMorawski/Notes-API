package com.filipmorawski.noteapi.note;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
interface NoteMapper {

	NoteDTO toDto(Note note);
	
	Note toEntity(NoteDTO dto);
	
	List<NoteDTO> toDtoList(List<Note> noteList);
	
}
