# ELifeDemo

 - [Download Apk](https://raw.githubusercontent.com/ddpatel2606/ELifeDemo/master/apk/app-release.apk)
 

Api Listing
 
 - [Task listing API](https://adam-deleteme.s3.amazonaws.com/tasks.json)
 
 ## Activities
 - MainActivity - Listing Tasks and sorting
 
 ## Tech stack & Open-source libraries
 - Minimum SDK level 23
 - [Kotlin](https://kotlinlang.org/) based, 
 - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
 - [Dagger dependency injection](https://developer.android.com/training/dependency-injection/dagger-basics)
 
 - JetPack
   - LiveData - notify domain layer data to views.
   - Lifecycle - dispose of observing data when lifecycle state changes.
   - ViewModel - UI related data holder, lifecycle aware.
   
 - Architecture
   - MVVM Architecture (Model View ViewModel - DataBinding)
   - Repository Design Pattern(Provides abstraction layer)
  
 - [RoomDatabase](https://developer.android.com/training/data-storage/room?authuser=1) - Support offline data.
 - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
 - [HttpInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - An OkHttp interceptor which logs HTTP request and response data.
 - [Gson](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
 - [Picasso](https://github.com/square/picasso) - loading images.
 - [Lottie](https://github.com/airbnb/lottie-android) - Splash screen animation.
 - [Timber](https://github.com/JakeWharton/timber) - logging.
 - [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
 - Custom Views
 - [CircularProgressView](https://github.com/rahatarmanahmed/CircularProgressView) - A polished and flexible CircleProgressView, fully customizable with animations.

## Images

```SplashActivity```


<img src="images/3.png" width="350"/>


```MainActivity : Light Mode```


<img src="images/4.png" width="350"/>


```MainActivity : Dark Mode```


<img src="images/1.png" width="350"/>
