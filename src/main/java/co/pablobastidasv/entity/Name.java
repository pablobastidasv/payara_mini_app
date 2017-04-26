package co.pablobastidasv.entity;

import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.json.Json;
import javax.json.JsonObject;

@Data
@Entity(noClassnameStored = true)
public class Name {

    @Id
    private String id;
    private String name;

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .build();
    }
}
