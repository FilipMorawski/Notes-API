package com.filipmorawski.noteapi.note;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

	private NoteService service;
	
	@Autowired	
	public NoteController(NoteService service) {
		this.service = service;
	}
	
	@ApiOperation(value="View a list of notes", response=List.class, responseContainer="Array")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List succesfully obtained"),
            @ApiResponse(code = 204, message = "List is empty")
    }
    )
	@GetMapping
	public ResponseEntity<List<NoteDTO>> getAll() {
		List<NoteDTO> noteList = service.getAll();
		if (noteList == null || noteList.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(noteList);
	}
	
	@ApiOperation(value="View a specific note", response=NoteDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note succesfully obtained"),
            @ApiResponse(code = 404, message = "Note you were trying to reach is not found")
    }
    )
	@GetMapping("/{id}")
	public ResponseEntity<NoteDTO> findOne( @PathVariable long id) {
		NoteDTO noteDTO = service.findOne(id);
		if (noteDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(noteDTO);
	}
	
	@ApiOperation(value="Create a new note", response=NoteDTO.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note succesfully created"),
    }
    )
	@PostMapping
	public ResponseEntity<NoteDTO> createNote() {
		NoteDTO noteDTO = service.createNote();
		if (noteDTO == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(noteDTO);
	}
	
	@ApiOperation(value="Update a note")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note succesfully updated"),
            @ApiResponse(code = 400, message = "Note in request body did not contain id, title or content"),
            @ApiResponse(code = 404, message = "Note you are trying to update is not found")
	}
	)
	@PutMapping
	public ResponseEntity<NoteDTO> updateNote(
		@Valid @RequestBody(required=true) NoteDTO dto) {
		if (!service.updateNote(dto)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value="Remove a note")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Note succesfully removed"),
            @ApiResponse(code = 404, message = "Note you are trying to remove is not found")
	}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<NoteDTO> deleteNote( @PathVariable long id) {
		if (!service.deleteNote(id)) {
			return ResponseEntity.notFound().build();
		}
		service.deleteNote(id);
		return ResponseEntity.ok().build();
	}
}
	
