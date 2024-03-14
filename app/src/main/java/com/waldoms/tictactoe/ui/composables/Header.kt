package com.waldoms.tictactoe.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Header(playerTurn: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    )
    {
        val playerBoxColor = if (playerTurn) {
            Color.Blue
        } else {
            Color.LightGray
        }
        val aiBoxColor = if (playerTurn) {
            Color.LightGray
        } else {
            Color.Red
        }

        Box(
            modifier = Modifier
                .width(100.dp)
                .background(playerBoxColor)
        )
        {
            Text(text = "Player", modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(50.dp))

        Box(
            modifier = Modifier
                .width(100.dp)
                .background(aiBoxColor)
        )
        {
            Text(text = "AI", modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
            )

        }
    }
}