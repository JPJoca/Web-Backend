package rs.raf.projekat.repositories.destination;

import rs.raf.projekat.entities.Destination;

import java.util.List;

public interface DestinationRepository {

    public List<Destination> getAllDestinations();

    public Destination getDestinationById(int id);

    public Destination createDestination(Destination destination);

    public Destination updateDestination(Destination destination , Integer id);

    public void deleteDestination(int id);
}
