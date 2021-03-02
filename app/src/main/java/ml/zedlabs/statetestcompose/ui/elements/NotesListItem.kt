package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.theme.purpleD3
import ml.zedlabs.statetestcompose.ui.theme.robotoCus

@Composable
fun NotesListItem(note: Note, editNote: (Note) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(true) { editNote(note) }

    ) {
        Column(
            modifier = Modifier.background(purpleD3)
                .padding(8.dp)
        ) {
            Text(
                text = note.title,
                style = robotoCus.body1,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.body,
                style = robotoCus.subtitle1,
                fontSize = 16.sp,
            )
        }

    }
}