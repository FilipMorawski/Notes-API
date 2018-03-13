package com.filipmorawski.noteapi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipmorawski.noteapi.note.NoteController;
import com.filipmorawski.noteapi.note.NoteDTO;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(value = NoteController.class, secure = false)
public class NoteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private NoteController controller;
	
	private NoteDTO  firstNote;
	private NoteDTO  secondNote;
	
	@Before
	public void setUp() throws Exception {
		Date date = new GregorianCalendar(2018,3,11).getTime();
		firstNote = new NoteDTO(1,"First Note", "First note content", date, date);
		secondNote = new NoteDTO(2,"Second Note", "Second note content", date, date);
	}

	@Test
	public void whenGetAll_shouldReturnListOfTwoNotes() throws Exception {
		List<NoteDTO> list = new ArrayList<NoteDTO>();
		list.add(firstNote);
		list.add(secondNote);
		ResponseEntity<List<NoteDTO>> re = ResponseEntity.ok(list);
		
		
		when(controller.getAll()).thenReturn(re);
		
		mockMvc .perform(get("/api/notes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is(firstNote.getTitle())))
				.andExpect(jsonPath("$[0].content", is(firstNote.getContent())))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].title", is(secondNote.getTitle())))
				.andExpect(jsonPath("$[1].content", is(secondNote.getContent())));
	}

	@Test
	public void whenFindOne_shouldReturnFirstNote() throws Exception {
		ResponseEntity<NoteDTO> re = ResponseEntity.ok(firstNote);
		
		when(controller.findOne(firstNote.getId())).thenReturn(re);
		
		mockMvc .perform(get("/api/notes/" + firstNote.getId()).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.title", is(firstNote.getTitle())))
		.andExpect(jsonPath("$.content", is(firstNote.getContent())));
	}
	
	@Test
	public void whenCreateNote_shouldReturnNoteWithDefaultValues() throws Exception {
		NoteDTO defaultNote = new NoteDTO(1,"default", "default", new Date(), new Date());
		ResponseEntity<NoteDTO> re = ResponseEntity.ok(defaultNote);
		
		when(controller.createNote()).thenReturn(re);
		
		mockMvc .perform(post("/api/notes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.title", is(defaultNote.getTitle())))
				.andExpect(jsonPath("$.content", is(defaultNote.getContent())));
	}
	
	@Test
	public void whenPutNoteDTO_shouldReturnOk() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ResponseEntity<NoteDTO> re = ResponseEntity.ok().build();
		
		when(controller.updateNote(firstNote)).thenReturn(re);
		
		mockMvc .perform(put("/api/notes")
				.content(mapper.writeValueAsString(firstNote))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
				
	}
	
	@Test
	public void whenDeleteById_shouldReturnOk() throws Exception {
		ResponseEntity<NoteDTO> re = ResponseEntity.ok().build();
		
		when(controller.deleteNote(firstNote.getId())).thenReturn(re);
		
		mockMvc .perform(delete("/api/notes/" + firstNote.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}
