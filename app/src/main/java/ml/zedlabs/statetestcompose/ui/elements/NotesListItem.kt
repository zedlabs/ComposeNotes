package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.theme.purpleD1
import ml.zedlabs.statetestcompose.ui.theme.purpleD3
import ml.zedlabs.statetestcompose.ui.theme.robotoCus

@Composable
fun NotesListItem(note: Note, editNote: (Note) -> Unit) {
    val vm: MainViewModel = viewModel()

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(true) {
                editNote(note)
            }

    ) {
        Column(
            modifier = Modifier
                .background(purpleD3)
                .padding(8.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .background(purpleD3)
                        .padding(8.dp)
                        .fillMaxWidth(.95f),
                    text = note.title ?: "",
                    style = robotoCus.body1,
                    fontSize = 20.sp,
                )

                Box(modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 10.dp)
                    .size(25.dp)
                    .clickable {
                        vm.deleteNote(note)
                    }) {
                    Icon(
                        imageVector = Icons.Sharp.Delete,
                        tint = purpleD1,
                        contentDescription = "delete-note",
                        modifier = Modifier.size(40.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.body ?: "",
                style = robotoCus.subtitle1,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }

    }


}