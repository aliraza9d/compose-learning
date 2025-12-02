package com.example.composepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

// ============================================
// 1. SCAFFOLD - Main App Structure
// ============================================
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Search", "Profile")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                actions = {
                    IconButton(onClick = { /* Action */ }) {
                        Icon(Icons.Default.Settings, "Settings")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* FAB Action */ }) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    ) { paddingValues ->
        // Main content with proper padding
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Content goes here with proper padding")
        }
    }
}

// ============================================
// 2. CARDS - Material Design Cards
// ============================================
@Preview
@Composable
fun CardExamples() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Basic Card
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Basic Card", style = MaterialTheme.typography.headlineSmall)
                Text("This is a simple card with content")
            }
        }

        // Elevated Card with Click
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* Handle click */ }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(Icons.Default.Favorite, "Icon", tint = Color.Red)
                Column {
                    Text("Clickable Card", style = MaterialTheme.typography.titleMedium)
                    Text("Tap me!", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        // Outlined Card
        OutlinedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Outlined Card", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Card with border outline")
            }
        }

        // Card with Image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box {
                // Image would go here
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Image Card", color = Color.White)
                    Text("With overlay text", color = Color.White)
                }
            }
        }
    }
}

// ============================================
// 3. BOTTOM SHEETS - Modal & Standard
// ============================================
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExamples() {
    var showModalSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showModalSheet = true }) {
            Text("Show Bottom Sheet")
        }
    }

    // Modal Bottom Sheet
    if (showModalSheet) {
        ModalBottomSheet(
            onDismissRequest = { showModalSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Bottom Sheet Title",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                repeat(3) { index ->
                    ListItem(
                        headlineContent = { Text("Option ${index + 1}") },
                        leadingContent = {
                            Icon(Icons.Default.Check, "Check")
                        },
                        modifier = Modifier.clickable {
                            // Handle selection
                            showModalSheet = false
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { showModalSheet = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close")
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
// Standard Bottom Sheet (Persistent)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardBottomSheetExample() {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Persistent Sheet", style = MaterialTheme.typography.titleLarge)
                Text("Swipe to expand/collapse")
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(20) {
                        Text(
                            "Item $it", modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }
        },
        sheetPeekHeight = 128.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Main Content")
        }
    }
}

// ============================================
// 4. DIALOGS - Alert & Custom
// ============================================
@Preview
@Composable
fun DialogExamples() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showCustomDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = { showAlertDialog = true }) {
            Text("Show Alert Dialog")
        }

        Button(onClick = { showCustomDialog = true }) {
            Text("Show Custom Dialog")
        }
    }

    // Alert Dialog
    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            icon = { Icon(Icons.Default.Warning, "Warning") },
            title = { Text("Confirm Action") },
            text = { Text("Are you sure you want to proceed?") },
            confirmButton = {
                TextButton(onClick = {
                    // Confirmed action
                    showAlertDialog = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlertDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Custom Dialog
    if (showCustomDialog) {
        Dialog(onDismissRequest = { showCustomDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Custom Dialog",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Enter text") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showCustomDialog = false }) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { showCustomDialog = false }) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}

// ============================================
// 5. PROGRESS INDICATORS
// ============================================
@Preview
@Composable
fun ProgressIndicatorExamples() {
    var progress by remember { mutableFloatStateOf(0.3f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Progress Indicators", style = MaterialTheme.typography.headlineMedium)

        // Circular Progress - Indeterminate
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Loading...")
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator()
        }

        // Circular Progress - Determinate
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("${(progress * 100).toInt()}% Complete")
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator(
                progress = { progress },
            )
        }

        // Linear Progress - Indeterminate
        Column {
            Text("Processing...")
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        // Linear Progress - Determinate
        Column {
            Text("Download Progress")
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Control progress
        Slider(
            value = progress,
            onValueChange = { progress = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// ============================================
// 6. CONSTRAINT LAYOUT
// ============================================
@Preview
@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create references
        val (title, image, description, button1, button2) = createRefs()

        // Title at top
        Text(
            text = "ConstraintLayout Demo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        // Image centered
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray)
                .constrainAs(image) {
                    top.linkTo(title.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        // Description below image
        Text(
            text = "This layout positions elements using constraints. Each element is positioned relative to others or the parent.",
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(image.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        // Button 1 at bottom left
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }
        ) {
            Text("Cancel")
        }

        // Button 2 at bottom right
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        ) {
            Text("Confirm")
        }

        // Create guidelines
        val guideline = createGuidelineFromTop(0.5f)
    }
}

@Preview
// Complex ConstraintLayout Example
@Composable
fun AdvancedConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (avatar, name, status, followers, following, bio) = createRefs()

        // Create a chain for followers and following
        createHorizontalChain(followers, following, chainStyle = ChainStyle.Spread)

        // Avatar
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color.Blue)
                .constrainAs(avatar) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )

        // Name next to avatar
        Text(
            text = "Abdul Wahab",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(avatar.top)
                start.linkTo(avatar.end, margin = 16.dp)
            }
        )

        // Status below name
        Text(
            text = "Active now",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray,
            modifier = Modifier.constrainAs(status) {
                top.linkTo(name.bottom, margin = 4.dp)
                start.linkTo(name.start)
            }
        )

        // Followers
        Column(
            modifier = Modifier.constrainAs(followers) {
                top.linkTo(avatar.bottom, margin = 24.dp)
                start.linkTo(parent.start)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("1.2K", style = MaterialTheme.typography.titleMedium)
            Text("Followers", style = MaterialTheme.typography.bodySmall)
        }

        // Following
        Column(
            modifier = Modifier.constrainAs(following) {
                top.linkTo(followers.top)
                end.linkTo(parent.end)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("856", style = MaterialTheme.typography.titleMedium)
            Text("Following", style = MaterialTheme.typography.bodySmall)
        }

        // Bio
        Text(
            text = "Android Developer | Kotlin Enthusiast | Coffee Lover ☕",
            modifier = Modifier.constrainAs(bio) {
                top.linkTo(followers.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
    }
}