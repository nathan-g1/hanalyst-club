package hanalyst.application.hanalystclub.Database;

import androidx.room.TypeConverter;
import hanalyst.application.hanalystclub.Entity.Temperature;

public class RoomConverter {
//    @TypeConverter
//    public String getTemperature(String temperature) {
//        return temperature.getValue() + " " + temperature.getScaleType();
//    }
//
//    @TypeConverter
//    public String setTemperature(String temp) {
//        return new Temperature(temp.split(" ")[0], Double.parseDouble(temp.split(" ")[1]));
//    }

    @TypeConverter
    public String getPlayingTeams(String[] teams) {
        return teams[0] + " " + teams[1];
    }

    @TypeConverter
    public String[] setPlayingTeams(String playingTeams) {
        return playingTeams.split(" ");
    }

}
