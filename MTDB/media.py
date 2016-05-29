import webbrowser


''' This is parent class '''
class Video():
    def __init__(self,movie_title,category):
        self.title= movie_title
        self.category =category
        

'''Movie class is used in class which stores the list of movies and its detail'''

class Movie(Video):
    ''' this class movie trailer'''
    MOVIE_RATING=["G","PG","PG-13","R"]
    def __init__(self,movie_title,movie_storyline,poster_img,trailer_youtube,category):
        Video.__init__(self,movie_title,category)
        self.storyline=movie_storyline
        self.poster_image_url=poster_img
        self.trailer_youtube_url=trailer_youtube
        
    def show_trailer(self):
        webbrowser.open(self.trailer_youtube_url)

''' TvShow class is not a part of this project but it is wriiten to make
program more  modular and add extra feature if needed in future'''

class TvShow(Video):
    ''' this class movie trailer'''
    MOVIE_RATING=["G","PG","PG-13","R"]
    def __init__(self,movie_title,movie_storyline,poster_img,trailer_youtube,category):
        Video.__init__(self,movie_title,category)
        self.storyline=movie_storyline
        self.poster_image_url=poster_img
        self.trailer_youtube_url=trailer_youtube
        
    def show_trailer(self):
        webbrowser.open(self.trailer_youtube_url)

