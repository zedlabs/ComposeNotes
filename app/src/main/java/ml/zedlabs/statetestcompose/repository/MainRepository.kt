package ml.zedlabs.statetestcompose.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.db.NotesDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val notesDao: NotesDao
) {

    fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()
        .flowOn(Dispatchers.Main)
        .conflate()


}