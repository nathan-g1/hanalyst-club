package hanalyst.application.hanalystclub.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import hanalyst.application.hanalystclub.Model.Attack;
import hanalyst.application.hanalystclub.Model.Defense;

public class AnalysisFactory {
    AnalysisData analysisData = new AnalysisData();
    String attackAnalysis = analysisData.getAttakAnalysis();
    String defenceAnalysis = analysisData.getDefenceAnalysis();

    public AnalysisFactory() throws JSONException {
    }

    JSONObject jsonObjectDefense = new JSONObject(defenceAnalysis);
    JSONArray jsonArrayDefense = jsonObjectDefense.getJSONArray("data");
    //
    JSONObject jsonObject = new JSONObject(attackAnalysis);
    JSONArray jsonArray = jsonObject.getJSONArray("data");

    public ArrayList<Attack> getAttackList() throws JSONException {
        ArrayList<Attack> arrayListAttack = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Attack attack = new Attack(jsonObject.getString("code"),
                    jsonObject.getString("desc"), jsonObject.getInt("value"));
            arrayListAttack.add(attack);
        }
        return arrayListAttack;
    }

    public ArrayList<Defense> getDefenseList() throws JSONException {
        ArrayList<Defense> arrayListDefense = new ArrayList<>();
        for (int i = 0; i < jsonArrayDefense.length(); i++) {
            JSONObject jsonObject = jsonArrayDefense.getJSONObject(i);
            Defense defense = new Defense(jsonObject.getString("code"),
                    jsonObject.getString("desc"), jsonObject.getInt("value"));
            arrayListDefense.add(defense);
        }
        return arrayListDefense;
    }

    public int getAttackAnalysis() {
        return attackAnalysis.length();
    }
}
