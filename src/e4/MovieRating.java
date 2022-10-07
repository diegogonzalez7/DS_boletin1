package e4;

public enum MovieRating {   //Enum externo con las valoraciones de las palículas.
    NOT_RATED(-1),
    AWFUL(0),
    BAD(2),
    MEDIOCRE(4),
    GOOD(6),
    EXCELLENT(8),
    MASTERPIECE(10);

    private final int NumericRating;

    MovieRating (int NumericRating){
        this.NumericRating = NumericRating;
    }

    public int getNumericRating(){
        return NumericRating;
    }

    public boolean isBetterThan(MovieRating movie){
        return this.NumericRating>movie.NumericRating;
    }
}
