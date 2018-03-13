package com.filipmorawski.noteapi.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class NoteService {
	
	private NoteRepository repo;
	private NoteMapper mapper;
	
	@Autowired
	NoteService(NoteRepository repo, NoteMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	List<NoteDTO> getAll() {
		return mapper.toDtoList(repo.findAll());
	}

	NoteDTO createNote() {
		return mapper.toDto(repo.save(new Note()));
	}

	NoteDTO findOne(long id) {
		return mapper.toDto(repo.findOne(id));
	}

	boolean updateNote(NoteDTO dto) {
		Note note = mapper.toEntity(dto);
		if (!repo.exists(note.getId())) {
			return false;
		}
		repo.save(note);
		return true;
	}

	boolean deleteNote(long id) {
		if(!repo.exists(id)) {
			return false;
		}
		repo.delete(id);
		return true;
	}
}
