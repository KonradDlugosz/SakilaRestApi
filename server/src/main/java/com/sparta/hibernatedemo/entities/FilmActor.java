package com.sparta.hibernatedemo.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "film_actor", indexes = {
        @Index(name = "idx_fk_film_id", columnList = "film_id")
})

public class FilmActor {
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @EmbeddedId
    private FilmActorId id;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }
}