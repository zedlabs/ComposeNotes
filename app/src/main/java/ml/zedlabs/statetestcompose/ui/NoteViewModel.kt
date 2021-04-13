package ml.zedlabs.statetestcompose.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.db.NotesDao
import ml.zedlabs.statetestcompose.repository.MainRepository
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    val notesDao: NotesDao,
    private val repository: MainRepository,
) : ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _body: MutableLiveData<String> = MutableLiveData("")
    val body: LiveData<String> = _body

    private var _noteId: MutableLiveData<Int> = MutableLiveData(-1)
    val noteId = _noteId

    private val author: MutableLiveData<String> = MutableLiveData("")

    fun getNote(id: Int) {
        if (id != -1) {
            _noteId.value = id
            viewModelScope.launch {
                repository.noteWithId(id).collect {
                    _title.value = it.title ?: ""
                    _body.value = it.body ?: ""
                    author.value = it.author
                }
            }
        }

    }

    //move local db operation to repository layer
    fun insertNote() {
        viewModelScope.launch {
            notesDao.insertNote(
                Note(
                    title = _title.value,
                    body = _body.value,
                    author = author.value!!
                )
            )
        }
    }

    fun updateNote() {
        viewModelScope.launch {
            notesDao.updateNote(
                Note(
                    id = _noteId.value!!,
                    title = _title.value,
                    body = _body.value,
                    author = author.value!!
                )
            )
        }
    }

    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateBody(body: String) {
        _body.value = body
    }
}
