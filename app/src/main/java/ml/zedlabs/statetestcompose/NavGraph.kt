package ml.zedlabs.statetestcompose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.NoteViewModel
import ml.zedlabs.statetestcompose.ui.elements.AddNote
import ml.zedlabs.statetestcompose.ui.elements.NotesList

object MainDestinations {
    const val NOTES_LIST = "notes-list"
    const val EDIT_NOTE = "edit-note"
    const val NOTE_ID_KEY = "noteId"
    const val NOTE_MODEL_KEY = "note"
}

@ExperimentalAnimationApi
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

        composable(
            route = "${MainDestinations.EDIT_NOTE}/{${MainDestinations.NOTE_ID_KEY}}",
            arguments = listOf(navArgument((MainDestinations.NOTE_ID_KEY)) {type = NavType.IntType })
        ) {
            val vm = hiltNavGraphViewModel<NoteViewModel>(backStackEntry = it)

            val noteId = it.arguments?.getInt(MainDestinations.NOTE_ID_KEY)
                ?: throw IllegalStateException("'noteId' shouldn't be null")

            vm.getNote(noteId)
            AddNote(vm, actions.upPress)
        }

    }
}

class MainActions(navController: NavHostController) {

    val editNote: (Note) -> Unit = { note: Note ->
        navController.currentBackStackEntry?.arguments?.putInt(
            MainDestinations.NOTE_MODEL_KEY,
            note.id
        )
        navController.navigate("${MainDestinations.EDIT_NOTE}/${note.id}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
