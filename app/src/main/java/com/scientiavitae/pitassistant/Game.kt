package com.scientiavitae.pitassistant

import java.io.Serializable

data class Game (
    val title: String,
    val description: String,
    val mainPayTable: HashMap<String, Int>,
    val sidePayTable1: HashMap<String, Int>,
    val sidePayTable2: HashMap<String, Int>,
    val sidePayTable3: HashMap<String, Int>,
    val strategy: String) : Serializable {





}