package hanalyst.application.hanalystclub.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hanalyst.application.hanalystclub.Entity.Attack;
import hanalyst.application.hanalystclub.Entity.Defense;

public class AnalysisFactory {

    private JSONObject jsonObject1;
    private JSONArray jsonArray;
    private JSONArray jsonArrayDefense;

    {
        try {
            jsonObject1 = new JSONObject("{\n" +
                    "        \"data\": [\n" +
                    "        {\n" +
                    "        \"id\": 0,\n" +
                    "        \"code\": \"PS\",\n" +
                    "        \"desc\": \"Passes - Short\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 1,\n" +
                    "        \"code\": \"PL\",\n" +
                    "        \"desc\": \"Passes – Long\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 2,\n" +
                    "        \"code\": \"DR\",\n" +
                    "        \"desc\": \"Dribbles\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 3,\n" +
                    "        \"code\": \"AS\",\n" +
                    "        \"desc\": \"Assists\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 4,\n" +
                    "        \"code\": \"CR\",\n" +
                    "        \"desc\": \"Crosses\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 5,\n" +
                    "        \"code\": \"HoT\",\n" +
                    "        \"desc\": \"Headers on target\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 6,\n" +
                    "        \"code\": \"OC\",\n" +
                    "        \"desc\": \"Offsides committed\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 7,\n" +
                    "        \"code\": \"FK\",\n" +
                    "        \"desc\": \"Free Kicks received\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 8,\n" +
                    "        \"code\": \"SoT\",\n" +
                    "        \"desc\": \"Shots on target\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 9,\n" +
                    "        \"code\": \"SoffT\",\n" +
                    "        \"desc\": \"Shots off target\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 10,\n" +
                    "        \"code\": \"GS\",\n" +
                    "        \"desc\": \"Goals Scored\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 11,\n" +
                    "        \"code\": \"C\",\n" +
                    "        \"desc\": \"Corners\",\n" +
                    "        \"value\": 0\n" +
                    "        },\n" +
                    "        {\n" +
                    "        \"id\": 12,\n" +
                    "        \"code\": \"HoffT\",\n" +
                    "        \"desc\": \"Headers off target\",\n" +
                    "        \"value\": 0\n" +
                    "        }\n" +
                    "        ]\n" +
                    "        }");
            jsonArray = jsonObject1.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Attack> getAttackList() {
        ArrayList<Attack> arrayListAttack = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject c = jsonArray.getJSONObject(i);
                Attack attack = new Attack(c.getString("code"), c.getString("desc"), c.getInt("value"));
                arrayListAttack.add(attack);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayListAttack;
    }

    {
        try {
            JSONObject jsonObjectDefense = new JSONObject("{  \"data\": [{\"id\": 0,\"code\": \"TL\",\"desc\": \"Tackles lost\",\"value\": 0},{\"id\": 1,\"code\": \"TW\",\"desc\": \"Tackles won\",\"value\": 0},{\"id\": 2,\"code\": \"ILP\",\"desc\": \"Interceptions of long pass\",\"value\": 0},{\"id\": 3,\"code\": \"ISP\",\"desc\": \"Interceptions of short pass\",\"value\": 0},{\"id\": 4,\"code\": \"CL\",\"desc\": \"Clearance\",\"value\": 0},{\"id\": 5,\"code\": \"CA\",\"desc\": \"Crosses against\",\"value\": 0},{\"id\": 6,\"code\": \"HW\",\"desc\": \"Headers won\",\"value\": 0},{\"id\": 7,\"code\": \"HL\",\"desc\": \"Headers lost\",\"value\": 0},{\"id\": 8,\"code\": \"OR\",\"desc\": \"Offsides received\",\"value\": 0},{\"id\": 9,\"code\": \"STR\",\"desc\": \"Shots on target received\",\"value\": 0},{\"id\": 10,\"code\": \"SofTR\",\"desc\": \"Shots off target received\",\"value\": 0},{\"id\": 11,\"code\": \"GC\",\"desc\": \"Goals conceded\",\"value\": 0},{\"id\": 12,\"code\": \"FKC\",\"desc\": \"Free Kick conceded\",\"value\": 0},{\"id\": 13,\"code\": \"PC\",\"desc\": \"Penalties conceded\",\"value\": 0},{\"id\": 14,\"code\": \"DM\",\"desc\": \"Defensive moves\",\"value\": 0},{\"id\": 15,\"code\": \"BC\",\"desc\": \"Body checks\",\"value\": 0},{\"id\": 16,\"code\": \"YC\",\"desc\": \"Yellow cards\",\"value\": 0},{\"id\": 17,\"code\": \"RC\",\"desc\": \"Red cards\",\"value\": 0}  ]}");
            jsonArrayDefense = jsonObjectDefense.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Defense> getDefenseList() {
        ArrayList<Defense> arrayListDefense = new ArrayList<>();
        for (int i = 0; i < jsonArrayDefense.length(); i++) {
            try {
                JSONObject jsonObject = jsonArrayDefense.getJSONObject(i);
                Defense defense = null;
                defense = new Defense(jsonObject.getString("code"),
                        jsonObject.getString("desc"), jsonObject.getInt("value"));
                arrayListDefense.add(defense);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayListDefense;
    }
}
