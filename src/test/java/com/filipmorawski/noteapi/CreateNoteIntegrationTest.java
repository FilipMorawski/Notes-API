package com.filipmorawski.noteapi;

import static org.assertj.core.api.Assertions.assertThat;
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
public class CreateNoteIntegrationTest {

	@Autowired
	private TestRestTemplate rest;
	
	@Test
	public void whenCreateNote_shouldReturnNoteAndOk() {
		//when
		ResponseEntity<NoteDTO> re = rest.postForEntity("/api/notes",null, NoteDTO.class);
		NoteDTO noteDTO = re.getBody();
		//then
		assertEquals(re.getStatusCode(), HttpStatus.OK);
		assertEquals(noteDTO.getId(), 1L);
		assertEquals(noteDTO.getTitle(), "default title");
		assertEquals(noteDTO.getContent(), "default content");
	}
}
