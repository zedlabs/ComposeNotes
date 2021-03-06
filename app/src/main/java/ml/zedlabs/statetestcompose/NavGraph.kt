package ml.zedlabs.statetestcompose

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.elements.AddNote
import ml.zedlabs.statetestcompose.ui.elements.NotesList

object MainDestinations {
    const val NOTES_LIST = "notes-list"
    const val EDIT_NOTE = "edit-note"
    const val NOTE_ID_KEY = "noteId"
    const val NOTE_MODEL_KEY = "note"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.NOTES_LIST) {

    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.NOTES_LIST) {
            val vm: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            NotesList(vm = vm, actions.editNote)
        }

        composable("${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_ID_KEY}}") {

            val note = navController.previousBackStackEntry?.arguments?.getParcelable<Note>(
                MainDestinations.NOTE_MODEL_KEY
            ) ?: throw IllegalArgumentException()

            val vm: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )

            AddNote(vm, note, actions.upPress)
        }

    }
}

class MainActions(navController: NavHostController) {

    val editNote: (Note) -> Unit = { note: Note ->
        navController.currentBackStackEntry?.arguments?.putParcelable(
            MainDestinations.NOTE_MODEL_KEY,
            note
        )
        navController.navigate("${MainDestinations.EDIT_NOTE}/${note.id}")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
