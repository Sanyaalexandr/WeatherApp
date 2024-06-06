package com.example.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.design.R

@Composable
fun Error(
    onUpdateClick: () -> Unit,
) {
    val errorText = stringResource(id = R.string.error_occurred)
    val updateText = stringResource(id = R.string.update)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            )
    ) {
        Text(
            text = errorText,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.height(42.dp))
        Button(onClick = onUpdateClick) {
            Text(
                text = updateText,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}