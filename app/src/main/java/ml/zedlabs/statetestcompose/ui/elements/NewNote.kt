package ml.zedlabs.statetestcompose.ui.elements

import android.content.Context
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ml.zedlabs.statetestcompose.db.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import ml.zedlabs.statetestcompose.ui.MainViewModel

@Composable
fun AddNote(vm: MainViewModel, note: Note, backPress: () -> Unit) {

    var title by rememberSaveable { mutableStateOf(note.title) }
    var body by rememberSaveable { mutableStateOf(note.body) }
    val ctx = LocalContext.current

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save") },
                icon = { Icon(imageVector = Icons.Sharp.Check , contentDescription = "Check")},
                onClick = {
                    when (note.title) {
                        "" -> {
                            if (title.isNotEmpty()) vm.insertNote(Note(title = title, body = body))
                        }
                        else -> {
                            vm.updateNote(
                                Note(id = note.id, title = title, body = body, author = note.author)
                            )
                        }
                    }

                    if (title.isNotEmpty()) backPress.invoke()
                    else ctx.makeShortToast("Title cant be empty!")
                },
            )

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

fun Context.makeShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}