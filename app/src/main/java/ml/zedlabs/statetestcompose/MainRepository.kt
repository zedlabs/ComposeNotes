package ml.zedlabs.statetestcompose

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    val notesDao: NotesDao
) {

    fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()
        .flowOn(Dispatchers.Main)
        .conflate()


}