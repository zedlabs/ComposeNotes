package ml.zedlabs.statetestcompose.ui.elements

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.db.Note
import ml.zedlabs.statetestcompose.ui.MainViewModel
import ml.zedlabs.statetestcompose.ui.theme.*

@Composable
fun AddNote(vm: MainViewModel, note: Note, backPress: () -> Unit) {

    StateTestComposeTheme {
        var title by rememberSaveable { mutableStateOf(note.title) }
        var body by rememberSaveable { mutableStateOf(note.body) }
        val ctx = LocalContext.current


        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Sharp.ArrowBack,
                        tint = purpleD1,
                        contentDescription = "back-buttom",
                        modifier = Modifier
                            .padding(end = 6.dp, top = 4.dp)
                            .size(30.dp)
                            .clickable {
                                backPress.invoke()
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "📝 Note",
                        style = robotoCus.subtitle2,
                        fontSize = 24.sp,
                    )

                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = " Note Title: ") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = purpleD0, unfocusedBorderColor = purpleD3
                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 90.dp),
                    value = body,
                    onValueChange = { body = it },
                    label = { Text(text = "Note Details: ") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = purpleD0, unfocusedBorderColor = purpleD3
                    ),
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = purpleD0),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(purpleD0)
                            .width(100.dp),
                        onClick = {
                            when (note.title) {
                                "" -> {
                                    if (title.isNotEmpty()) vm.insertNote(
                                        Note(title = title, body = body)
                                    )
                                }
                                else -> {
                                    vm.updateNote(
                                        Note(id = note.id, title = title, body = body, author = note.author)
                                    )
                                }
                            }

                            if (title.isNotEmpty()) backPress.invoke()
                            else ctx.makeShortToast("Title cant be empty!")
                        }) {
                        Text(
                            modifier = Modifier.padding(bottom = 4.dp),
                            text = "Save",
                            color = Color.White,
                            style = robotoCus.subtitle2,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }


}

fun Context.makeShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}