# TV Series

TV Series is a simple app that allows to overview details of the top most popular tv series. User can overview the list of the shows and tap on one in order to see its name, network, start date, end date and status. It is also possible to see show's fullsize image.

Application has also the list of user's favorite tv series. User can add show to favorites by clicking heart icon on details page.
![tv_series_1](https://user-images.githubusercontent.com/56269299/116295885-dcbada00-a799-11eb-90bf-31444ccea868.png)
![tv_series_2](https://user-images.githubusercontent.com/56269299/116296152-286d8380-a79a-11eb-8e60-68041a7a5b06.png)

Project was built according to the MVP architecture and uses Dagger2 for dependency injection. In order to fetch data from API Retrofit2 was used. Application uses Room library to create local database and manage it.

To display images in application Glide library was helpful.

API source: [episodate.com/api](https://www.episodate.com/api).
Background image comes from [pixabay.com](https://pixabay.com).
