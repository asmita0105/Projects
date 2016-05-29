import media
import fresh_tomatoes


# list of movies
# instance of movie class
toy = media.Movie(" Toy Story ",
                  "A story of boy and his toys",
                  "https://upload.wikimedia.org/wikipedia/en/1/13/Toy_Story.jpg",  # noqa
                  "https://www.youtube.com/watch?v=KYz2wyBy3kc",  # noqa
                  "Movies")

avatar = media.Movie("Avatar",
                     "A marine on alian planet",
                     "https://upload.wikimedia.org/wikipedia/en/b/b0/Avatar-Teaser-Poster.jpg",  # noqa
                     "https://www.youtube.com/watch?v=uZNHIU3uHT4",  # noqa
                     "movies")

twilight = media.Movie("Twilight",
                       "Story of vampire",
                       "https://upload.wikimedia.org/wikipedia/en/9/93/The_Twilight_Saga-_New_Moon_poster.JPG",  # noqa
                       "https://www.youtube.com/watch?v=bw5cgdK6tWg",  # noqa
                       "movies")

jungleBook = media.Movie("The Jungle book",
                         "Story of boy growing up in jungle",
                         "http://i0.wp.com/bitcast-a-sm.bitgravity.com/slashfilm/wp/wp-content/images/JB_Triptych_1-Sht_Center_Online_v4_lg.jpg",  # noqa
                         "https://www.youtube.com/watch?v=C4qgAaxB_pc",  # noqa
                         "movies")

forestGump = media.Movie("Forrest Gump",
                         "Forrest Gump, while not intelligent, has accidentally been present at many historic moments, but his true love, Jenny Curran, eludes him",   # noqa
                         "https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg",  # noqa
                         "https://www.youtube.com/watch?v=uPIEn0M8su0",  # noqa
                         "movies")

catchMe = media.Movie("Catch Me If You Can",
                      "The true story of Frank Abagnale Jr. who, before his 19th birthday, successfully conned millions of dollars' worth of checks as a Pan Am pilot, doctor, and legal prosecutor.",  # noqa
                      "https://upload.wikimedia.org/wikipedia/en/4/4d/Catch_Me_If_You_Can_2002_movie.jpg",  # noqa
                      "https://www.youtube.com/watch?v=71rDQ7z4eFg",  # noqa
                      "movies")


'''Instance of other chaild class TvShow.
   this is for future if we want to add some
   categories we can use instance of TvShow '''

breakingbad = media.TvShow("Breaking Bad",
                           "A high school chemistry teacher turn to drug dealer",  # noqa
                           "https://upload.wikimedia.org/wikipedia/en/6/65/Walter_White2.jpg",  # noqa
                           "https://www.youtube.com/watch?v=HhesaQXLuRY",  # noqa
                           "tvshow")

friends = media.TvShow("Friends",
                       "Story of six friends",
                       "https://upload.wikimedia.org/wikipedia/en/6/65/Walter_White2.jpg",  # noqa
                       "https://www.youtube.com/watch?v=HhesaQXLuRY",  # noqa
                       "tvshow")

tvShows = [breakingbad, friends]

''' list of movies'''
movie = [toy, avatar, twilight, jungleBook, forestGump, catchMe]

fresh_tomatoes.open_movies_page(movie)