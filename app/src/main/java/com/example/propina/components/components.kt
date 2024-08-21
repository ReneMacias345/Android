package com.example.propina.components

import android.text.InputFilter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RounedIconButton(
    modifier: Modifier= Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: CardElevation = CardDefaults.cardElevation(4.dp)
){

    Card(modifier = modifier.padding(4.dp).size(40.dp).clickable { onClick.invoke() },
        shape = CircleShape,
        colors= CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = elevation
        ){

        Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()){
            Icon(imageVector = imageVector, contentDescription = "",tint = tint)
        }

    }

}


@Composable
fun Inputfield(
    modifier: Modifier = Modifier,
    valueState:MutableState<String>,
    labelId:String,
    enabled: Boolean= true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
){


    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = {Text(text = labelId)},
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "")
        },
        singleLine = isSingleLine,
        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp).fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )

}

@Preview
@Composable
fun RounedIconButtonPreview() {

    Column {

        val testInput = remember{ mutableStateOf("")}
            Inputfield(valueState = testInput, labelId = "Prueba")
            Inputfield(valueState = testInput, labelId = "Nombre")
            RounedIconButton(
                imageVector = Icons.Filled.Add,
                onClick = { },
                tint = Color.Blue
            )
            RounedIconButton(
                imageVector = Icons.Filled.BrokenImage,
                onClick = { },
                tint = Color.Black
            )
            RounedIconButton(
                imageVector = Icons.Filled.AddLink,
                onClick = { },
                tint = Color.Red
            )
    }
}