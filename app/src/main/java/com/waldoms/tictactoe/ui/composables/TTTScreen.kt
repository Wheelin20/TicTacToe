package com.waldoms.tictactoe.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class Win {
    PLAYER,
    AI,
    DRAW
}

fun checkEndGames(m: List<Boolean?>): Win?
{
    var win: Win? = null
    if(
        m[0] == true && m[1] == true && m[2] == true ||
        m[3] == true && m[4] == true && m[5] == true ||
        m[6] == true && m[7] == true && m[8] == true ||
        m[0] == true && m[3] == true && m[6] == true ||
        m[1] == true && m[4] == true && m[7] == true ||
        m[2] == true && m[5] == true && m[8] == true ||
        m[0] == true && m[4] == true && m[8] == true ||
        m[2] == true && m[4] == true && m[6] == true
    )
    {
        win = Win.PLAYER
    }
    else if(
        m[0] == false && m[1] == false && m[2] == false ||
        m[3] == false && m[4] == false && m[5] == false ||
        m[6] == false && m[7] == false && m[8] == false ||
        m[0] == false && m[3] == false && m[6] == false ||
        m[1] == false && m[4] == false && m[7] == false ||
        m[2] == false && m[5] == false && m[8] == false ||
        m[0] == false && m[4] == false && m[8] == false ||
        m[2] == false && m[4] == false && m[6] == false
    )
    {
        win = Win.AI
    }
    else
    {
        if(!m.contains(null))
        {
            win = Win.DRAW
        }
    }

    return win
}

@Composable
fun TTTScreen() {
    // true - player's turn.  false - AI's turn
    val playerTurn = remember { mutableStateOf(true) }

    // true - player move.  false - AI's move.  null - no move
    val moves = remember {
        mutableStateListOf<Boolean?>(
            null, null, null,
            null, null, null,
            null, null, null
        )
    }

    val win = remember {
        mutableStateOf<Win?>(null)
    }

    val onTap: (Offset) -> Unit = {
        if(playerTurn.value && win.value == null)
        {
            val x = (it.x / 333).toInt()
            val y = (it.y / 333).toInt()
            val positionInMoves = (y * 3) + x

            if(moves[positionInMoves] == null)
            {
                moves[positionInMoves] = true
                playerTurn.value = false
                win.value = checkEndGames(moves)
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Tick Tac Toe", fontSize = 30.sp, modifier = Modifier.padding(16.dp))
        Header(playerTurn.value)

        Board(moves = moves, onTap = onTap)
        if(!playerTurn.value && win.value == null)
        {
            CircularProgressIndicator(color = Color.Red, modifier = Modifier.padding(16.dp))
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch {
                    delay(1500L)
                    while (!playerTurn.value) {
                        val i: Int = Random.nextInt(9)
                        if (moves[i] == null) {
                            moves[i] = false
                            playerTurn.value = true
                            win.value = checkEndGames(moves)
                        }
                    }
                }
            }
        }
        if(win.value != null)
        {
            when(win.value)
            {
                Win.PLAYER ->
                {
                    Text(text = "Player has won \uD83C\uDF89", fontSize = 25.sp)
                }
                Win.AI ->
                {
                    Text(text = "AI has won \uD83D\uDE24", fontSize = 25.sp)
                }
                Win.DRAW ->
                {
                    Text(text = "It's a draw \uD83D\uD333", fontSize = 25.sp)
                }
                else -> {}
            }

            Button(onClick = {
                playerTurn.value = true
                win.value = null
                for (i in 0..8) { moves[i] = null }
            }) {
                Text(text = "Click to start over")
            }
        }
    }
}