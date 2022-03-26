# Currency Converter
**Currency Converter** is application which fetches the realtime currency values from API and converts the desired currency of the user.

<img src="https://github.com/AdityaShidlyali/CurrencyConverterApp/blob/main/images/currency_converter.jpg" />

## Features
* User can enter the desired conversion fromats.
* Conversion can be done accross 170+ currencies as specified by the API.
* Material design guidelines followed to increase the user experience.

## Project structure (MVVM)
* di
* helper
* models
* network
* repositories
* view
* viewmodels

## Android :heart: Koltin
* Retrofit is used with ScalarConverters to get the JSON response as the string.
* Default repository pattern is used for effective error handling and ease of testing by providing mock repositories.
* Hilt framework is used for dependency injection.

## Tech stack used
- [Retrofit 2](https://square.github.io/retrofit/) - REST client for making network calls.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For asynchronous operations.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Lifecycle aware library to manage data observing the lifecycle of licecycle owner.
- [Hilt-Dagger](https://dagger.dev/hilt/) - Recommended Dependency Injection Framework for Android.
- [Kotlin Flows](https://developer.android.com/kotlin/flow) - Emits sequence of values or data, and consumer asynchronously consumes these values or data.

## Licence
```
MIT License

Copyright (c) 2022 Aditya Shidlyali

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
