package hanalyst.application.hanalystclub.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hanalyst.application.hanalystclub.Model.Attack;
import hanalyst.application.hanalystclub.Model.Defense;

public class AnalysisFactory {
    private JSONArray jsonArrayDefense;
    private JSONObject jsonObjectDefense;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public AnalysisFactory() {
    }


    {
        try {
            jsonObjectDefense = new JSONObject("{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"id\": 0,\n" +
                    "      \"code\": \"TL\",\n" +
                    "      \"desc\": \"Tackles lost\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 1,\n" +
                    "      \"code\": \"TW\",\n" +
                    "      \"desc\": \"Tackles won\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 2,\n" +
                    "      \"code\": \"ILP\",\n" +
                    "      \"desc\": \"Interceptions of long pass\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 3,\n" +
                    "      \"code\": \"ISP\",\n" +
                    "      \"desc\": \"Interceptions of short pass\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 4,\n" +
                    "      \"code\": \"CL\",\n" +
                    "      \"desc\": \"Clearance\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 5,\n" +
                    "      \"code\": \"CA\",\n" +
                    "      \"desc\": \"Crosses against\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 6,\n" +
                    "      \"code\": \"HW\",\n" +
                    "      \"desc\": \"Headers won\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 7,\n" +
                    "      \"code\": \"HL\",\n" +
                    "      \"desc\": \"Headers lost\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 8,\n" +
                    "      \"code\": \"OR\",\n" +
                    "      \"desc\": \"Offs\"\n" +
                    "      id\n" +
                    "      \"es received\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 9,\n" +
                    "      \"code\": \"STR\",\n" +
                    "      \"desc\": \"Shots on target received\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 10\n" +
                    "      \"code\": \"SofTR\",\n" +
                    "      \"desc\": \"Shots off target received\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 11,\n" +
                    "      \"code\": \"GC\",\n" +
                    "      \"desc\": \"Goals conceded\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 12,\n" +
                    "      \"code\": \"FKC\",\n" +
                    "      \"desc\": \"Free Kick conceded\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 13,\n" +
                    "      \"code\": \"PC\",\n" +
                    "      \"desc\": \"Penalties conceded\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 14,\n" +
                    "      \"code\": \"DM\",\n" +
                    "      \"desc\": \"Defensive moves\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 15,\n" +
                    "      \"code\": \"BC\",\n" +
                    "      \"desc\": \"Body checks\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 16,\n" +
                    "      \"code\": \"YC\",\n" +
                    "      \"desc\": \"Yellow cards\",\n" +
                    "      \"value\": 0\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": 17,\n" +
                    "      \"code\": \"RC\",\n" +
                    "      \"desc\": \"Red cards\",\n" +
                    "      \"value\": 0\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}");
            jsonArrayDefense = jsonObjectDefense.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    {
        try {
            jsonObject = new JSONObject("{\n" +
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
            jsonArray = jsonObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Attack> getAttackList() {
        ArrayList<Attack> arrayListAttack = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Attack attack = null;
                attack = new Attack(jsonObject.getString("code"),
                        jsonObject.getString("desc"), jsonObject.getInt("value"));
                arrayListAttack.add(attack);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayListAttack;
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
