package com.example.body_fit

import androidx.compose.ui.Modifier

fun BottomNavigation(modifier: Modifier, function: () -> BottomNavigationItem){

}
class BottomNavigationItem(
    icon: () -> Unit,
    label: () -> Unit,
    selected: Boolean,
    onclick: () -> Unit
) {

}