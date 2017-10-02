package io.egen.api.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Wind {

    @Id
    private String id;

    private String speed;
    private String degree;

    @JsonIgnore
    @OneToOne(mappedBy = "wind")
    public Weather weather;

    public Wind () {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "id='" + id + '\'' +
                ", speed=" + speed +
                ", degree=" + degree +
                ", weather=" + weather +
                '}';
    }
}
