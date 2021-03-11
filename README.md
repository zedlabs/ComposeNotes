# ComposeNotes
Notes app with full jetpack compose architecture (UI + navigation). Uses MVVM, Room, Kotlin Flows &amp; LiveData
  </br>
  
 [![Kotlin Version](https://img.shields.io/badge/Kotlin-1.4.30-blue.svg)](https://kotlinlang.org)
###### *Minimal Notes App for Android*

the goal of this project is to create a android notes app using compose navigation and UI

<img src="https://github.com/zedlabs/ComposeNotes/blob/master/screenshots/sc01.png" width="270" height="550">    <img src="https://github.com/zedlabs/ComposeNotes/blob/master/screenshots/sc02.png" width="270" height="550">   <img src="https://github.com/zedlabs/ComposeNotes/blob/master/screenshots/sc03.png" width="270" height="550"> 

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) 
    * [Hilt](https://github.com/google/dagger) - DI
    * [Jetpack](https://developer.android.com/jetpack)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Compose](https://developer.android.com/jetpack/compose) - UI, navigation
* Architecture
    * Model-View-ViewModel
 
 * Todo
   * Create navigation transitions animations([Issue](https://issuetracker.google.com/issues/172112072))
