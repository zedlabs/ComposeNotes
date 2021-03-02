package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.theme.StateTestComposeTheme
import ml.zedlabs.statetestcompose.ui.theme.purpleD0
import ml.zedlabs.statetestcompose.ui.theme.purpleD1
import ml.zedlabs.statetestcompose.ui.theme.robotoCus

@Composable
fun NotesList(vm: MainViewModel, editNote: (Note) -> Unit) {

    val notes by vm.notes.observeAsState()
    StateTestComposeTheme {
        Scaffold(
            topBar = {
                NotesListTopBar()
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = {   Text(
                        text = "New Note",
                        style = robotoCus.subtitle2,
                        fontSize = 16.sp,
                        modifier = Modifier.wrapContentHeight(Alignment.CenterVertically)
                            .padding(bottom = 2.dp)
                    )},
                    onClick = { editNote(Note(title = "", body = "")) },
                    icon = { Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add Icon") },
                    backgroundColor = purpleD0
                )
            }
        ) {
            LazyColumn {
                itemsIndexed(items = notes.orEmpty()) { _, note ->
                    NotesListItem(note, editNote)
                }
            }
        }

    }

}

@Composable
fun NotesListTopBar() {
    Row(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "📝 Notes",
            style = robotoCus.subtitle2,
            fontSize = 24.sp,
        )
        Icon(
            imageVector = Icons.Sharp.Search,
            tint = purpleD1,
            contentDescription = "Add Icon",
            modifier = Modifier.weight(1f)
                .wrapContentWidth(Alignment.End)
                .padding(end = 6.dp, top = 4.dp)
                .size(30.dp)
        )
    }

}