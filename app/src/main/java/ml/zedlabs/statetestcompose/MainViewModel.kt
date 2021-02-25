package ml.zedlabs.statetestcompose

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    dao: NotesDao,
    repository: MainRepository,
) : ViewModel() {

    val notes: LiveData<List<Note>> =
        repository.getAllNotes().asLiveData()

    init {
        var i = 0
        viewModelScope.launch {
            while (true){
                dao.insertNote(Note(
                    0,
                    "${i++}",
                    "",
                    ""
                ))
                delay(3000)
            }

        }

    }

}