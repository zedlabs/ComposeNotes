package ml.zedlabs.statetestcompose

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val notesDao: NotesDao,
    repository: MainRepository,
) : ViewModel() {

    val notes: LiveData<List<Note>> =
        repository.getAllNotes().asLiveData()

    fun insertNote(note: Note){
        viewModelScope.launch {
            notesDao.insertNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            notesDao.deleteNote(note)
        }
    }

}