package dev.baires.exercise.core.view

interface Initializer {

    fun init(){
        references()
        observables()
        actions()
    }

    fun references()
    fun actions()
    fun observables()

}