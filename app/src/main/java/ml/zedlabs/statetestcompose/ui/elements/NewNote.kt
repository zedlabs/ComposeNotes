package ml.zedlabs.statetestcompose.ui.elements

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ml.zedlabs.statetestcompose.ui.NoteViewModel
import ml.zedlabs.statetestcompose.ui.theme.*

@Composable
fun AddNote(vm: NoteViewModel, backPress: () -> Unit) {

    StateTestComposeTheme {

        val title by vm.title.observeAsState()
        val body by vm.body.observeAsState()
        val id = vm.noteId.observeAsState()
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
                            .padding(end = 6.dp, top = 8.dp)
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
                    .defaultMinSize(minHeight = 5000.dp)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = title ?: "",
                    onValueChange = { vm.updateTitle(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = " Note Title ") },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = purpleD0, unfocusedBorderColor = purpleD3
                    ),
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 90.dp),
                    value = body ?: "",
                    onValueChange = { vm.updateBody(it) },
                    label = { Text(text = "Note Details ") },
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
                            if (title!!.isNotEmpty()) {
                                when (id.value) {
                                    -1 -> { vm.insertNote() }
                                    else -> { vm.updateNote() }
                                }
                                backPress.invoke()
                            } else ctx.makeShortToast("Title cant be empty!")
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