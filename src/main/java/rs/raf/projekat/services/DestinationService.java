package rs.raf.projekat.services;

import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.repositories.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.List;

public class DestinationService {

    @Inject
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations(){
        return this.destinationRepository.getAllDestinations();
    }

    public Destination getDestinationById(int id){
        return this.destinationRepository.getDestinationById(id);
    }

    public Destination createDestination(Destination destination){
       return this.destinationRepository.createDestination(destination);
    }

    public Destination updateDestination(Destination destination , Integer id){
        return this.destinationRepository.updateDestination(destination, id);
    }

    public void deleteDestination(int id){
        this.destinationRepository.deleteDestination(id);
    }
}
