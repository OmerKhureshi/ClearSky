package io.egen.api.entitiy;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name="Weather.findAll", query="SELECT w FROM Weather w"),
        @NamedQuery(name="Weather.findAllCities", query="SELECT distinct(w.city) FROM Weather w order by w.city"),
        @NamedQuery(name="Weather.findCityByName", query="SELECT w FROM Weather w  where w.city = :pCity order by w.timestamp desc")
})
public class Weather {

    @Id
    String id;

    private String city;
    private String description;
    private String humidity;
    private String pressure;
    private String temperature;

    @OneToOne(cascade = {CascadeType.ALL})
    public Wind wind;

    // @JsonFormat(pattern = "yyyy-mm-ddThh:mm:ss.SSSZ")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", columnDefinition="TIMESTAMP(3)")
    // @Type(type = "timestamp")
    private Date timestamp;

    public Weather() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // @JsonFormat(pattern = "yyyy-mm-ddThh:mm:ss.SSSZ")
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", wind=" + wind +
                ", timestamp=" + timestamp +
                '}';
    }
}
