package ml.zedlabs.statetestcompose

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notesDao: NotesDao,
    private val repository: MainRepository
) : ViewModel() {

    val notes: LiveData<List<Note>> =
        repository.getAllNotes().asLiveData()

    fun insertNote(note: Note) {
        viewModelScope.launch {
            notesDao.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            notesDao.updateNote(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            notesDao.deleteAllNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesDao.deleteNote(note)
        }
    }

}