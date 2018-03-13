package com.filipmorawski.noteapi.note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
class NoteMapperImpl implements NoteMapper {

	@Override
	public NoteDTO toDto(Note note) {
		if(note == null) {
			return null;
		}
		return new NoteDTO(note.getId(), 
				note.getTitle(), 
				note.getContent(), 
				note.getCreateDate(), 
				note.getModifyDate());
	}

	@Override
	public Note toEntity(NoteDTO dto) {
		if (dto == null) {
			return null;
		}
		Note note = new Note();
		note.setId(dto.getId());
		note.setTitle(dto.getTitle());
		note.setContent(dto.getContent());
		note.setCreateDate(dto.getCreateDate());
		note.setModifyDate(dto.getModifyDate());
		return note;
	}

	@Override
	public List<NoteDTO> toDtoList(List<Note> noteList) {
		if(noteList == null) {
			return null;
		}
		List<NoteDTO> dtoList = new ArrayList<NoteDTO>();
		Iterator<Note> iterator = noteList.iterator();
		while (iterator.hasNext()) {
			Note note = iterator.next();
			if(note == null) {
				return null;
			}
			NoteDTO dto = toDto(note);
			dtoList.add(dto);
		}
		return dtoList;
	}
}
