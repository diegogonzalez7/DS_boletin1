package e4;

import java.util.ArrayList;

import java.util.List;

import static e4.MovieRating.NOT_RATED;


public class Movie {
    public  List Rating_list = new ArrayList();
    private final String title;

    public Movie(String title) {
        this.title = title;
        this.Rating_list.add(MovieRating.NOT_RATED);
    }

    public String getTitle() {
        return this.title;
    }

    public void insertRating(MovieRating rating){
        this.Rating_list.add(rating);
    }

    private boolean isRated(){  //Recorre la lista viendo si hay alguna valoración distinta de NOT_RATED
        int List_size = this.Rating_list.size() , i=0;
        for(;i<List_size;i++) {
            if(this.Rating_list.get(i) != NOT_RATED) return true;
        }
        return false;
    }
    public MovieRating maximumRating () {   //Recorre la lista almacenando en maximum la mejor valoración.
        MovieRating maximum = NOT_RATED;
        for (Object o : this.Rating_list) {
            if (!maximum.isBetterThan((MovieRating) o)) {
                maximum = (MovieRating) o;
            }
        }
        return maximum;
    }
    public double averageRating() { //Recorre la lista excluyendo las no valoradas y sumando la valoración de las que sí lo están
        float suma=0,conteo=0;

        if (!isRated()) throw new java.util.NoSuchElementException();
        for (Object o : this.Rating_list) {
            if ( o != NOT_RATED) {
                conteo++;
                suma += ((MovieRating) o).getNumericRating();
            }
        }
        return suma/conteo;
    }
}