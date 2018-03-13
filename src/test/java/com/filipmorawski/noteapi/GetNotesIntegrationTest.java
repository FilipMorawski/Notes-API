package com.filipmorawski.noteapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.filipmorawski.noteapi.note.NoteDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetNotesIntegrationTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Test
	public void whenGetAll_shouldReturnListOfTwo() {
		//when
		NoteDTO firstNote = rest.postForEntity("/api/notes",null,NoteDTO.class).getBody();
		NoteDTO secondNote = rest.postForEntity("/api/notes",null,NoteDTO.class).getBody();

		ResponseEntity<NoteDTO[]> re = rest.getForEntity("/api/notes", NoteDTO[].class);
		NoteDTO[] list = re.getBody();

		//then
		assertEquals(re.getStatusCode(), HttpStatus.OK);
		assertEquals(list[0].getId(), firstNote.getId());
		assertEquals(list[0].getTitle(), firstNote.getTitle());
		assertEquals(list[0].getContent(), firstNote.getContent());
		assertEquals(list[1].getId(), secondNote.getId());
		assertEquals(list[1].getTitle(), secondNote.getTitle());
		assertEquals(list[1].getContent(), secondNote.getContent());
	}
	
	@Test
	public void whenGetById_shouldReturnSpecificNote() {
		//when
		NoteDTO firstNote = rest.postForEntity("/api/notes",null,NoteDTO.class).getBody();
		NoteDTO secondNote = rest.postForEntity("/api/notes",null,NoteDTO.class).getBody();
		
		ResponseEntity<NoteDTO> re = rest.getForEntity("/api/notes/" + secondNote.getId(), NoteDTO.class);
		NoteDTO obtainedNote = re.getBody();
		//then
		
		assertEquals(re.getStatusCode(), HttpStatus.OK);
		assertEquals(obtainedNote.getId(), secondNote.getId());
		assertEquals(obtainedNote.getTitle(), secondNote.getTitle());
		assertEquals(obtainedNote.getContent(), secondNote.getContent());
	}
}
