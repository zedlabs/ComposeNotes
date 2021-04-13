package ml.zedlabs.statetestcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.statetestcompose.db.NoteDatabase
import ml.zedlabs.statetestcompose.db.NotesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {

    @Singleton
    @Provides
    fun provideDao(noteDatabase: NoteDatabase): NotesDao =
        noteDatabase.getNotesDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-db"
        ).fallbackToDestructiveMigration().build()

}