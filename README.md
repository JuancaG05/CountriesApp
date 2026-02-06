# CountriesApp
This is an Android native app written in Kotlin, used as a simple example to test different useful tools for Android projects.

It's inspired in different countries with their flags, capitals and some more details! 🚩

The app connects with the REST API in [https://rickandmortyapi.com/graphql](https://restcountries.com/v3.1/), and it can do the next stuff:
- Show the list of countries with their flag and name
- Show a country details screen when tapping on a country in the list
- Working offline (if you already have retrieved the countries with connection previously)

Moreover, it contains some unit tests and it's structured with a clean architecture

## App architecture
The app follows a **Clean architecture** approach:

![Clean architecture](https://github.com/user-attachments/assets/9f10a478-bdc6-48c0-b681-e6695fafd54e)
*(Source: from my own)*

This architecture, structured in layers, states that there can only be dependencies towards inner layers, and that the domain is the center of the architecture. That way, we follow the separation-of-concerns principle and in general all
of the SOLID principles, helping increase cohesion and decrease coupling. The result of all of this is a maintainable and quality app.

A brief description of the different layers:
- **UI:** this layer contains the logic related to UI, that is, what user can see. It is divided mainly in 2 parts: *UI components*, which represent visual components, and *State-holders*, which make calculations needed by the UI
and connects with lower layers.
- **Data:** where the actual access to data is made. It mainly consists of *repositories*, which take care of working with different information resources and returning data in a usable state, and *data sources*, which access just one
information source (local or remote)
- **Domain:** in the center of the architecture we can find this layer, which essentially contains the *use cases*, which represent actions made by the user with the app, and the *model*, which is the representation of the domain model of the app.

## Tools used
A list of the different tools and technologies used for different purposes are:
- **Jetpack Compose** for UI
- **Retrofit** as web client for the REST API
- **Moshi** as parser
- **Koin** for dependency injection
- **Coil** for image display
- **Room** as database manager
- **MockK** for test mocks

## What's next?
This is just a kind of PoC (proof of concept)! So, of course, it can be improved in several ways 😀. To finish with the required functionality, that couldn't be finished due to time restrictions, we would have to add:
- [ ] Search functionality (which shouldn't be difficult now that we have a clean architecture!)
- [ ] Unit tests for CountriesRepository, CountriesRemoteDataSource, CountriesDao, RetrieveCountriesUseCase, CountriesViewModel, and maybe UI tests for the composables


Some of the proposals for the future are:
- [ ] Structure the app in different modules: right now, different layers are represented in different packages. This is because the app is still a small one, but it would be better to have different modules, that will help with efficiency,
will make the app scale better and make the separation-of-concerns principle be even more fulfilled!
- [ ] Make UI prettier: effort here was not put mainly in UI/UX, so yes, it can be improved a lot
- [ ] Improve screen state management: in this case, we're not handling different screen status such as Loading, Failure, Success... But it should be done like that
- [ ] Add failure management: we sticked to the ideal case (no errors from network) due to time limit, but this is an important thing to add as well, for example with a wrapper Result class
- [ ] Improve navigation: it could be made more abstract, right now is very coupled to the MainActivity
- [ ] Some other things that I might forget adding here 💡
