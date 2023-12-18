import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun App() {
    MaterialTheme {

        var isDialogShowed by remember {
            mutableStateOf(false)
        }

        val notesList = remember {
            mutableStateListOf(
                *Constant.notes.map { it }.toTypedArray()
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = { TopAppBar { Text("CMNotesApp") } },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            isDialogShowed = true
                        }
                    ){
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                }
            ) { paddingValues->
                Box(modifier = Modifier.padding(paddingValues)){
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        verticalItemSpacing = 16.dp,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        items(notesList){note->
                            NoteItemUi(note = note)
                        }
                    }
                }
            }
        }

        if(isDialogShowed){
            UpdateNoteDialog(
                onAddNote = {note->
                    notesList.add(note)
                },
                onDismissRequest = {
                    isDialogShowed = false
                }
            )
        }
    }
}

@Composable
fun NoteItemUi(modifier: Modifier = Modifier,note: Note) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(note.color))
    ){
        Text(
            text = note.desc,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(16.dp)
        )
    }
}


@Composable
fun UpdateNoteDialog(
    onAddNote : (Note)->Unit,
    onDismissRequest : ()->Unit
){
    var selectedColor by remember { 
        mutableStateOf(Constant.colors[0])
    }

    var text by remember {
        mutableStateOf("")
    }
    
    Dialog(onDismissRequest = onDismissRequest){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            backgroundColor = Color(selectedColor)
        ) { 
            Column(modifier = Modifier.wrapContentHeight(), 
                horizontalAlignment = Alignment.CenterHorizontally) { 
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Constant.colors.forEach { color->
                        ColorBox(
                            color=color,
                            onSelectColor = {
                                selectedColor = color
                            }
                        )
                    }
                }
                TextField(
                    value = text,
                    onValueChange = {text = it},
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(selectedColor),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = false,
                    modifier = Modifier.height(200.dp).fillMaxWidth()
                )

                IconButton(onClick = {
                    onAddNote(Note(text, selectedColor))
                    onDismissRequest()
                }){
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun ColorBox(color: Long, onSelectColor : ()->Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(color))
            .border(width = 2.dp, color = Color.White, shape = CircleShape)
            .clickable { onSelectColor() }
    )
}
