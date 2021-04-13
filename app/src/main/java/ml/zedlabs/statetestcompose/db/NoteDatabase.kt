package ml.zedlabs.statetestcompose.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun getNotesDao() : NotesDao
}