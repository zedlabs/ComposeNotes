package ml.zedlabs.statetestcompose.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.zedlabs.statetestcompose.repository.MainRepository
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.db.NotesDao
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notesDao: NotesDao,
    private val repository: MainRepository
) : ViewModel() {

    var _searchViewVisible = MutableLiveData(false)
    val searchViewVisible: LiveData<Boolean> = _searchViewVisible

    fun searchViewVisibility(isVisible: Boolean){
        _searchViewVisible.value = isVisible
    }

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