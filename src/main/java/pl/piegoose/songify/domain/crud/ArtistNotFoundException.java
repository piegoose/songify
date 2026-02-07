package pl.piegoose.songify.domain.crud;

class ArtistNotFoundException extends RuntimeException {
    ArtistNotFoundException(final String message) {
        super("artist with id: " + message+" not found..");
    }
}
