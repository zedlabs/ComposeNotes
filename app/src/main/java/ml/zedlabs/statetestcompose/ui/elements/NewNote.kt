package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ml.zedlabs.statetestcompose.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun addNote(note: Note){
    Column {
        var title by remember { mutableStateOf(note.title) }
        var body by remember { mutableStateOf(note.body) }
        Text(
            text = "Title",
            modifier = Modifier.padding(bottom = 8.dp), style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title")}
        )
    }

}