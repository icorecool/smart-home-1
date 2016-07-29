package com.myhome.recorder;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.myhome.recorder.json.NLURes;
import com.myhome.recorder.json.RObject;
import com.myhome.recorder.json.RecognizeData;
import com.myhome.recorder.json.Result;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class RecorgnizeJudgeResult {

    private final RecognizeData recognizeData;
    private final NLURes nluRes;


    public RecorgnizeJudgeResult(RecognizeData recognizeData) {
        this.recognizeData = recognizeData;
        nluRes = new Gson().fromJson(recognizeData.getJsonRes(), NLURes.class);
    }


    public boolean isLightOn() {
        if (nluRes != null && !Strings.isNullOrEmpty(nluRes.getParsedText())) {
            List<Result> results = nluRes.getResults();
            if (results.size() > 0) {
                RObject object = results.get(0).getObject();
                return object != null && "flashlight_on".equals(object.getSettingtype());
            }
        }
        return false;
    }

    public boolean isLightOff() {
        if (nluRes != null && !Strings.isNullOrEmpty(nluRes.getParsedText())) {
            List<Result> results = nluRes.getResults();
            if (results.size() > 0) {
                RObject object = results.get(0).getObject();
                return object != null && "flashlight_off".equals(object.getSettingtype());
            }
        }
        return false;
    }
}
