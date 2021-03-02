package ml.zedlabs.statetestcompose.ui.elements

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ml.zedlabs.statetestcompose.db.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import ml.zedlabs.statetestcompose.ui.MainViewModel

@Composable
fun AddNote(vm: MainViewModel, note: Note, backPress: () -> Unit) {

    var title by remember { mutableStateOf(note.title) }
    var body by remember { mutableStateOf(note.body) }
    val ctx = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                when (note.title) {
                    "" -> {
                        if(title.isNotEmpty()) vm.insertNote(Note(title = title, body = body))
                    }
                    else -> {
                        vm.updateNote(
                            Note(id = note.id, title = title, body = body, author = note.author)
                        )
                    }
                }

                if(title.isNotEmpty()) backPress.invoke()
                else ctx.makeShortToast("Title cant be empty!")
            }) {
                Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add Note")
            }

        }
    ) {
        Column {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") }
            )
            OutlinedTextField(
                value = body,
                onValueChange = { body = it },
                label = { Text(text = "Body") }
            )
        }
    }

}
fun Context.makeShortToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}