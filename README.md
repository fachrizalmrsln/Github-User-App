# Github-User-App
A simple GitHub user app to find someone on GitHub and their repositories.

# Features
1. Searching GitHub user profile (Search anyone in GitHub so you can see thier information)
3. Viewing GitHub profile details and their repository (Look user GitHub details and their list of repository on GitHub)
4. Caching Data (Caching your recent search so it will be available in offline-mode if you want to re-search that user again)
5. Unit & Instrument Testable (Test case for Unit & Instrument test scenario in some functionality on this app)

# Tech Stack
- View Binding
- CoroutineScope
- MVVM
- Flow
- LiveData
- Room
- Mockito
- MockWebServer
- Espresso

# How The Magic Works
1. Launching App, Splash Screen will delaying UI for 3seconds.
2. In the Search Screen, you just need to type someone's else name, lets said fachrizal.
3. After your trigger the search button inside your device keyboard, the magic will search for fachrizal inside your local database.
4. If fachrizal is exist inside your local database, then the magic just gonna deliver that data to your Search Screen so you can see the results and interact with that.
5. If fachrizal does not exist inside your local database, then the magic will do some network request for searching it on the github API.
6. If the network request success, the magic will store that result to your local database so in the further if you search for fachrizal again, you will instantly get step-4, and deliver that data to your Search Screen so you can see the results and interact with that.
7. If the network request fail, the magic will catch the fail execption and deliver the error message to UI so you will know what is going wrong.
8. After you've got what your looking for in Search Screen and clicking one from the list, the magic will brings you to Detail Screen.
9. Inside Detail Screen, the magic will search for the detail data in your local database, referencing from user name from the user you've choose from Search Screen.
10. If the data is exist inside your local database, the the magic will show the detail user data to you so you can see the user details and its repositories list on their github profile.
11. If the data is not exist inside your local database, the magic will do some network request for searching it on the github API.
12. If the network request success, the magic will store that result to your local database so in the further if you reopen that user details again, you will able to instantly get that data.
13. If the network request fail, the magic will catch the fail execption and deliver the error message to UI so you will know what is going wrong.

Give a shot to look deeper in this project!

# Authors
Fachrizal A. Z. Mursalin
@fachrizalmrsln

