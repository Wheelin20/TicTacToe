package com.waldoms.tictactoe.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.waldoms.tictactoe.R

@Composable
fun Board(moves: List<Boolean?>, onTap: (Offset) -> Unit)
{
    Box(modifier = Modifier
        .aspectRatio(1f)
        .padding(32.dp)
        .background(Color.LightGray)
        .pointerInput(Unit)
        {
            detectTapGestures(onTap = onTap)
        }
    )
    {
        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize(1f))
        {
            Row(modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(1f)
                .background(Color.Black)){}
            Row(modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(1f)
                .background(Color.Black)){}
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize(1f))
        {
            Column(modifier = Modifier
                .width(2.dp)
                .fillMaxHeight(1f)
                .background(Color.Black)){}
            Column(modifier = Modifier
                .width(2.dp)
                .fillMaxHeight(1f)
                .background(Color.Black)){}
        }
        Column(modifier = Modifier.fillMaxSize(1f))
        {
            for (i in 0..2)
            {
                Row(modifier = Modifier.weight(1f))
                {
                    for(j in 0..2)
                    {
                        Column(modifier = Modifier.weight(1f))
                        {
                            getComposableFromMove(move = moves[(i*3)+j])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getComposableFromMove(move: Boolean?)
{
    when (move)
    {
        true ->
        {
            Image(
                painter = painterResource(id = R.drawable.ic_x),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(1f),
                colorFilter = ColorFilter.tint(Color.Blue)
            )
        }
        false ->
        {
            Image(
                painter = painterResource(id = R.drawable.ic_o),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(1f),
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
        null ->
        {
            Image(
                painter = painterResource(id = R.drawable.ic_null),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(1f),
            )
        }
    }
}