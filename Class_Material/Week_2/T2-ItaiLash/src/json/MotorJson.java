package json;
import vehicle.*;
import com.google.gson.*;
import java.lang.reflect.Type;

public  class MotorJson {
    static String star = "********************************";
    static boolean print = true;
    /**
     * Simple Serialization example
     */
    private static String carSerializeVehicle(){
        System.out.println(star+"carSerializeVehicle"+star+"\n");
        Car kiaPicanto = new Car("Kia", "Picanto", "409-94-301", 959, 173, 5);
        Gson gson = new Gson();
        String jsonS = gson.toJson(kiaPicanto);
        System.out.println(jsonS);
        return jsonS;
    }

    /**
     * Simple Deserialization example
     */
    private static void carDeSerialization(){
        System.out.println(star+"carDeSerialization"+star+"\n");
        String kiaJson = "{\"manufacturer\":\"Kia\",\"model\":\"Picanto\",\"registrationPlate\":\"409-94-301\",\"weight\":959.0,\"maxSpeed\":173,\"seats\":5}";
        String kiaJsonNice = "{'manufacturer':'Kia','model':'Picanto','registrationPlate':'409-94-301','weight':959.0,'maxSpeed':173,'seats':5}";
        Gson gson = new Gson();
        Car kia ;
        Car kiaNice ;
        kia = gson.fromJson(kiaJson, Car.class);
        kiaNice = gson.fromJson(kiaJsonNice, Car.class);
        System.out.println(kia);
        System.out.println(kiaNice);

    }

    /**
     * Nested Serialization example
     */
    private static void nestedCarSerializeVehicle(){
        System.out.println(star+"nestedCarSerializeVehicle"+star+"\n");
        Car kiaPicanto = new Car("Kia", "Picanto", "409-94-301", 959, 173, 5);
        kiaPicanto.setEngineId("0645343424");
        kiaPicanto.setEngineName("GDI turbo I3");
        Gson gson = new Gson();
        String jsonS = gson.toJson(kiaPicanto);
        System.out.println(jsonS);
    }


    /**
     * Nested Deserialization example
     */
    private static void nestedCarDeSerialization(){
        System.out.println(star+"nestedCarDeSerialization"+star+"\n");
        String kiaJson = "{\"manufacturer\":\"Kia\",\"model\":\"Picanto\",\"registrationPlate\":\"409-94-301\",\"weight\":959.0,\"maxSpeed\":173,\"seats\":5,\"engine\":{\"engineName\":\"GDI turbo I3\",\"id\":\"0645343424\"}}";
        Gson gson = new Gson();
        Car kia ;
        kia = gson.fromJson(kiaJson, Car.class);
        System.out.println(kia);
    }

    /**
     * Nested automatic Deserialization example - this method will return all fields empty !
     */
    private static void nestedCarDeSerializationAutoFail(){
        System.out.println(star+"nestedCarDeSerialization"+star+"\n");
        String kiaJson = "{\"car_producer\":\"Kia\",\"car_model\":\"Picanto\",\"car_id\":\"409-94-301\",\"car_weight\":959.0,\"car_max_speed\":173,\"car_seats\":5,\"engine_name\":\"GDI turbo I3\",\"engine_id\":\"0645343424\"}";
        System.out.println(kiaJson);
        Car kia ;
        Gson gson = new Gson();
        kia = gson.fromJson(kiaJson, Car.class);
        System.out.println(kia);
    }

    /**
     * Nested automatic Deserialization example.
     */
    private static void nestedCarDeSerializationManual(){
        System.out.println(star+"nestedCarDeSerializationManual"+star+"\n");
        String kiaJson = "{\"car_producer\":\"Kia\",\"car_model\":\"Picanto\",\"car_id\":\"409-94-301\",\"car_weight\":959.0,\"car_max_speed\":173,\"car_seats\":5,\"engine_name\":\"GDI turbo I3\",\"engine_id\":\"0645343424\"}";
        GsonBuilder gsonBuilder = new GsonBuilder();

        // change serialization for specific types
        JsonDeserializer<Car> deserializer = new JsonDeserializer<Car>() {
            @Override
            public Car deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                Car car = new Car(
                        jsonObject.get("car_producer").getAsString(),
                        jsonObject.get("car_model").getAsString(),
                        jsonObject.get("car_id").getAsString(),
                        jsonObject.get("car_weight").getAsInt(),
                        jsonObject.get("car_max_speed").getAsInt(),
                        jsonObject.get("car_seats").getAsInt()
                );

                car.setEngineName(jsonObject.get("engine_name").getAsString());
                car.setEngineId(jsonObject.get("engine_id").getAsString());
                return car;
            }};


        gsonBuilder.registerTypeAdapter(Car.class, deserializer);
        Gson customGson = gsonBuilder.create();
        Car car =  customGson.fromJson(kiaJson, Car.class);
        System.out.println(car);

    }

    public static void main(String[] args) {
//        carSerializeVehicle();
//        carDeSerialization();
//       nestedCarSerializeVehicle();
//       nestedCarDeSerialization();
        nestedCarDeSerializationAutoFail();
//        nestedCarDeSerializationManual();
    }
}
