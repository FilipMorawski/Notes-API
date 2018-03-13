package com.filipmorawski.noteapi.note;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.filipmorawski.noteapi.note.NoteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class NoteRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired 
	private NoteRepository repo;
	
	private Note firstNote; 
	private Note secondNote; 
	
	@Before
	public void setup() {
		firstNote = new Note();
		firstNote.setTitle("first Note");
		firstNote.setContent("first note content");
		
		secondNote = new Note();
		secondNote.setTitle("second Note");
		secondNote.setContent("second note content");
		em.persist(firstNote);
		em.flush();
		em.persist(secondNote);
		em.flush();
	}
	
	@Test
	public void whenFindById_shouldReturnNoteWithSpecificName() {
		
		//when
		Note foundNote = repo.findOne(2L);
		//than
		assertThat(foundNote.getTitle()).isEqualTo(secondNote.getTitle());
	}

	@Test
	public void whenFindAll_shouldReturnTwoNotes() {
		
		//when
		List<Note> notes = repo.findAll();
		
		//then
		assertThat(notes.size()).isEqualTo(2);
		assertThat(notes.get(0)).isEqualTo(firstNote);
		assertThat(notes.get(1)).isEqualTo(secondNote);
	}
	
	@Test
	public void whenSave_ShouldReturnUpdatedNote() {
		//when
		Note noteToUpdate = new Note();
		noteToUpdate.setId(1);
		noteToUpdate.setTitle("Modified Title");
		noteToUpdate.setContent("Modified content");
		
		repo.save(noteToUpdate);
		Note updatedNote = repo.findOne(1L);
		
		//then
		assertThat(updatedNote.getTitle()).isEqualTo(noteToUpdate.getTitle());
		assertThat(updatedNote.getContent()).isEqualTo(noteToUpdate.getContent());
	}
	
	@Test
	public void whenDeleteByID_shouldReturnListWithOneNote() {
		//when
		repo.delete(1L);
		
		List<Note> notes = repo.findAll();
		
		assertThat(notes.size()).isEqualTo(1);
		assertThat(notes.get(0).getTitle()).isEqualTo(secondNote.getTitle());
	}
	
	@Test
	public void whenDeleteByEntity_shouldReturnListWithOneNote(){
		repo.delete(firstNote);
		
		List<Note> notes = repo.findAll();
		
		assertThat(notes.size()).isEqualTo(1);
		assertThat(notes.get(0).getTitle()).isEqualTo(secondNote.getTitle());
	}
}
