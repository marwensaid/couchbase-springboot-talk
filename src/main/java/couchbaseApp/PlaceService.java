package couchbaseApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by msaidi on 11/6/15.
 */

@Service
public class PlaceService {
    private final Facebook facebook;
    private final CouchbaseTemplate couchbaseTemplate;
    private final PlaceRepository placeRepository;

    @Autowired
    PlaceService(Facebook facebook, CouchbaseTemplate couchbaseTemplate,
                 PlaceRepository placeRepository) {
        this.facebook = facebook;
        this.couchbaseTemplate = couchbaseTemplate;
        this.placeRepository = placeRepository;
    }

    public List<String> search(String query, double lat, double lon, int distance) {
        return facebook.placesOperations().search(query, lat, lon, distance).stream()
                .map(p -> this.placeRepository.save(new Place(p))).map(Place::getId)
                .collect(Collectors.toList());
    }
}


