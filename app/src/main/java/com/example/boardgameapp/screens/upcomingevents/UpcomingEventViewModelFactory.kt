//package com.example.boardgameapp.screens.upcomingevents
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.boardgameapp.database.BoardGameDao
//import com.example.boardgameapp.database.BoardGameRepository
//
//class UpcomingEventViewModelFactory (private val dao :BoardGameDao): ViewModelProvider.Factory{
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(UpcomingEventsViewModel::class.java)){
//            return UpcomingEventsViewModel(dao) as T
//        }
//        throw IllegalArgumentException("Unknown View Model class")
//    }
//}