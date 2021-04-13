package ml.zedlabs.statetestcompose.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ml.zedlabs.statetestcompose.db.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :titleParam || '%' ")
    fun searchQuery(titleParam: String): Flow<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun findNoteById(noteId: Int): Flow<Note>
}