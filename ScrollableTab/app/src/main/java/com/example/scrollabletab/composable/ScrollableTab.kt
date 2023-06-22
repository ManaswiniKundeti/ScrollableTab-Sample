package com.example.scrollabletab.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableTab() {
    val tabList  = listOf("ALL", "tab 1", "tab 2", "tab 3", "tab 4", "tab 5")
    var selectedTabIndex by remember { mutableStateOf(0) }
    
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            modifier = Modifier
                .border(2.dp, Color.Red)
                .padding(5.dp)
                .background(Color.Gray),
//            contentColor = Color.White,

        ) {
            tabList.forEachIndexed { index, tab ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index},
                    text = { Text(text = tab)}
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            val selectedTabName = tabList[selectedTabIndex]
            DisplayContentForTab(selectedTabName)
        }
    }
}

@Composable
fun DisplayContentForTab(tabName: String) {
    Column() {
        var expanded by remember { mutableStateOf(false) }
        val itemCount = if (expanded) 10 else 3
        ElevatedButton(
            onClick = { expanded = !expanded },
        ) {
            Text(if (expanded) "Show less" else "Show more")
        }
        LazyColumn() {
            items(itemCount) { index ->
                Text(
                    text = "Content for $tabName: Item $index",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                )
            }
        }


    }

}