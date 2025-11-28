package id.bytezilla.mysimplejetblog.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.bytezilla.mysimplejetblog.ui.BlogViewModel
import id.bytezilla.mysimplejetblog.ui.components.BlogItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: BlogViewModel,
    navigateToDetail: (Long) -> Unit,
    navigateToAbout: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Simple Jet Blog") },
                actions = {
                    IconButton(
                        onClick = navigateToAbout,
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "about_page"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addBlog()
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Blog")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SearchBar(
                query = query,
                onQueryChange = viewModel::searchBlogs,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (uiState.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Data Kosong")
                }
            } else {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(bottom = 80.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState, key = { it.id }) { blog ->
                        BlogItem(
                            blog = blog,
                            modifier = Modifier.clickable { navigateToDetail(blog.id) },
                            onDeleteClick = viewModel::removeBlog
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(text = "Cari judul blog...")
        },
        singleLine = true,
        modifier = modifier
    )
}
