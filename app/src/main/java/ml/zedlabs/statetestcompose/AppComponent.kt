package ml.zedlabs.statetestcompose

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
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
    fun providesAppDatabase(@ApplicationContext context: Context) : NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note-db"
        ).build()

//    @Provides
//    @Singleton
//    fun provideRepository(notesDao: NotesDao): MainRepository {
//        return MainRepository(notesDao)
//    }
}