package domain;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class ElectionRepository implements PanacheRepositoryBase<Election, String> {

    public void submit(Election election) {
    }

}
