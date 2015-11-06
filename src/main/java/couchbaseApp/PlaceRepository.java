package couchbaseApp;

import com.couchbase.client.protocol.views.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by msaidi on 11/6/15.
 */
interface PlaceRepository extends CrudRepository<Place, String> {

    // @View(designDocument = "place", viewName = "byName")
    Collection<Place> findByName(Query placeNameQuery);
}