package ml.zedlabs.statetestcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ml.zedlabs.statetestcompose.ui.elements.notesListItem
import ml.zedlabs.statetestcompose.ui.theme.StateTestComposeTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesList()
        }
    }
}

@Composable
fun NotesList(vm: MainViewModel = viewModel()) {
    val notes by vm.notes.observeAsState()
    StateTestComposeTheme {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Create Note") },
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add Icon") }
                )
            }
        ) {
            LazyColumn {
                itemsIndexed(items = notes.orEmpty()) { _, note ->
                    notesListItem(note)
                }
            }
        }

    }

//    val navController = rememberNavController()

//    NavHost(navController, startDestination = "notes-list") {
//        composable("notes-list") { notesList() }
//        composable("new-note") {  addNote() }
//    }

}


