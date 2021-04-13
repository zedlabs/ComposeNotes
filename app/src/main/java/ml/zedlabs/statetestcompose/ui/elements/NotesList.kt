package ml.zedlabs.statetestcompose.ui.elements

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.theme.*

@Composable
fun NotesList(vm: MainViewModel, editNote: (Note) -> Unit) {

    val notes by vm.notes.observeAsState()
    val searchParam by vm.searchParam.observeAsState("")

    StateTestComposeTheme {
        Scaffold(
            topBar = {
                NotesListTopBar(vm, searchParam)
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = "New Note",
                            style = robotoCus.subtitle2,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(bottom = 2.dp)
                        )
                    },
                    onClick = { editNote(Note(id = -1, title = "", body = "")) },
                    icon = { Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add Icon") },
                    backgroundColor = purpleD0
                )
            }
        ) {
            LazyColumn {
                itemsIndexed(
                    items = notes.orEmpty().filter { it.title?.contains(searchParam)!! }) { _, note ->
                    NotesListItem(note, editNote)
                }
            }
        }

    }

}

@Composable
fun NotesListTopBar(vm: MainViewModel, searchParam: String) {

    val isSearchBarVisible by vm.searchViewVisible.observeAsState(false)

    Crossfade(targetState = isSearchBarVisible) { isVisible ->
        when (isVisible) {
            true -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    OutlinedTextField(
                        placeholder = { Text(text = "Enter Search Query") },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 14.sp),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(start = 8.dp),
                        value = searchParam, onValueChange = {
                            vm.updateSearchParam(it)
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = purpleD5
                        )
                    )
                    Icon(
                        imageVector = Icons.Sharp.Clear,
                        tint = purpleD1,
                        contentDescription = "clear-icon",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(end = 6.dp, top = 16.dp)
                            .size(30.dp)
                            .clickable {
                                vm.searchViewVisibility(false)
                                vm.updateSearchParam("")
                            }
                    )
                }

            }
            false -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "📝 Notes",
                        style = robotoCus.subtitle2,
                        fontSize = 24.sp,
                    )
                    Icon(
                        imageVector = Icons.Sharp.Search,
                        tint = purpleD1,
                        contentDescription = "Add Icon",
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                            .padding(top = 16.dp, end = 8.dp)
                            .size(30.dp)
                            .clickable {
                                vm.searchViewVisibility(true)
                            }
                    )
                }
            }
        }

    }
}