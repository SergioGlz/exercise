package dev.baires.exercise.core.repository.models

data class Projects(val items: ArrayList<Project>)

data class Project (
    val full_name: String,
    val html_url: String,
    val description: String
)