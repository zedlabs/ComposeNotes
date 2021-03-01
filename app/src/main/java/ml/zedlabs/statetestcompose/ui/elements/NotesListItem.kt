package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ml.zedlabs.statetestcompose.Note

@Composable
fun NotesListItem(note: Note, editNote: (Note) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable(true) { editNote(note) }
    ) {
        Column {
            Text(text = note.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.body)
        }

    }
}