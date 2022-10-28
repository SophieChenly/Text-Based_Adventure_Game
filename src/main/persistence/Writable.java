package persistence;
//CITATION: This file is modeled after the provided sample application

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
