package eu.ase.ro.lab11.utils;

public interface Constants {
    //constants used to pass data between activities
    String USER_INFO_KEY = "sendUserInfo";
    String USER_NAME_KEY = "sendUserName";
    String USER_AGE_KEY = "sendUserAge";
    //the key used by the intent to store the movie object that will be sent to MainActivity
    final String MOVIE_KEY = "sendMovie";

    //costants used to send data between activity and fragment
    String KEY_ACTIVITY_TO_FRAGMENT = "sendDataBetweenActivityFragment";
    String KEY_ACTIVITY_TO_FRAGMENT_INT = "sendIntBetweenActivityFragment";

    //constants used to parse files
    String KEY_JSON_ARRAY = "movies";
    String KEY_MOVIE_TITLE = "title";
    String KEY_MOVIE_RELEASE_DATE = "releaseDate";
    String KEY_MOVIE_PROFIT = "profit";
    String KEY_MOVIE_MOVIE_GENRE = "movieGenre";
    String KEY_MOVIE_PLATFORM = "platform";
    String KEY_NODE_XML = "Movie";

    String SHARED_PREFERENCES_NAME = "aboutPreferences";
    String RATING_BAR_ABOUT_VALUE = "ratingBarValue";
}
