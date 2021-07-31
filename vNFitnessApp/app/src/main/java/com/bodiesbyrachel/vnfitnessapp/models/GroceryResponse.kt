package com.bodiesbyrachel.vnfitnessapp.models

data class GroceryResponse(
    val success: Boolean? = null,
    val groceryList: List<GroceryList>? = null
)

data class GroceryList(
    val name: String? = null,
    val items: List<Items>? = null
)

data class Items(
    val itemName: String? = null
)