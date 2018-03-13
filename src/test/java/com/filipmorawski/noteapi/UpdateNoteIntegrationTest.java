package com.filipmorawski.noteapi;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.filipmorawski.noteapi.note.NoteDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UpdateNoteIntegrationTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Test
	public void whenGetAll_shouldReturnListOfTwo() {
		//when
		NoteDTO firstNote = rest.postForEntity("/api/notes",null,NoteDTO.class).getBody();
		NoteDTO noteToUpdate = firstNote;
		
		noteToUpdate.setTitle("Updated Note");
		noteToUpdate.setContent("Updated Content");
		
		HttpEntity<NoteDTO> ent = new HttpEntity<NoteDTO>(noteToUpdate);
		ResponseEntity<NoteDTO> firstRe = rest.exchange("/api/notes", HttpMethod.PUT, ent, NoteDTO.class);
		
		ResponseEntity<NoteDTO> secondRe = rest.getForEntity("/api/notes/" + firstNote.getId(), NoteDTO.class);
		
		NoteDTO noteFromDatabase = secondRe.getBody();
		//then
		assertEquals(firstRe.getStatusCode(), HttpStatus.OK);
		assertEquals(noteFromDatabase.getId(), noteToUpdate.getId());
		assertEquals(noteFromDatabase.getTitle(), noteToUpdate.getTitle());
		assertEquals(noteFromDatabase.getContent(), noteToUpdate.getContent());
	}
}
