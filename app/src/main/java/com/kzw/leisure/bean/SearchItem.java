package com.kzw.leisure.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.kzw.leisure.utils.Constant.MAP_STRING;


/**
 * author: kang4
 * Date: 2019/9/23
 * Description:
 */
public class SearchItem implements Serializable {
    //0:ok资源网，1：最大资源网，2：永久资源网 3:酷哈资源
    public String name;
    public String url;
    public String siteName;
    public int type;
    private Map<String, String> variableMap;
    private String variable;

    public String videoSourceUrl;
    private String ruleSeriesList;
    private String ruleSeriesName;
    private String ruleSeriesNoteUrl;
    private String rulePlayType;
    private String ruleItem;
    private String ruleTypeList;
    private String ruleVideoName;
    private String ruleVideoImage;

    public String getRuleVideoName() {
        return ruleVideoName;
    }

    public void setRuleVideoName(String ruleVideoName) {
        this.ruleVideoName = ruleVideoName;
    }

    public String getRuleVideoImage() {
        return ruleVideoImage;
    }

    public void setRuleVideoImage(String ruleVideoImage) {
        this.ruleVideoImage = ruleVideoImage;
    }

    public String getRuleTypeList() {
        return ruleTypeList;
    }

    public void setRuleTypeList(String ruleTypeList) {
        this.ruleTypeList = ruleTypeList;
    }
    public String getRuleItem() {
        return ruleItem;
    }

    public void setRuleItem(String ruleItem) {
        this.ruleItem = ruleItem;
    }
    public String getRulePlayType() {
        return rulePlayType;
    }

    public void setRulePlayType(String rulePlayType) {
        this.rulePlayType = rulePlayType;
    }


    public String getRuleSeriesList() {
        return ruleSeriesList;
    }

    public void setRuleSeriesList(String ruleSeriesList) {
        this.ruleSeriesList = ruleSeriesList;
    }

    public String getRuleSeriesName() {
        return ruleSeriesName;
    }

    public void setRuleSeriesName(String ruleSeriesName) {
        this.ruleSeriesName = ruleSeriesName;
    }

    public String getRuleSeriesNoteUrl() {
        return ruleSeriesNoteUrl;
    }

    public void setRuleSeriesNoteUrl(String ruleSeriesNoteUrl) {
        this.ruleSeriesNoteUrl = ruleSeriesNoteUrl;
    }

    public String getVideoSourceUrl() {
        return videoSourceUrl;
    }

    public void setVideoSourceUrl(String videoSourceUrl) {
        this.videoSourceUrl = videoSourceUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SearchItem() {

    }

    public SearchItem(String name, String url, String siteName) {
        this.name = name;
        this.url = url;
        this.siteName=siteName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getVariable() {
        return this.variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void putVariable(String key, String value) {
        if (variableMap == null) {
            variableMap = new HashMap<>();
        }
        variableMap.put(key, value);
        variable = new Gson().toJson(variableMap);
    }

    public Map<String, String> getVariableMap() {
        if (variableMap == null) {
            return new Gson().fromJson(variable, MAP_STRING);
        }
        return variableMap;
    }



}
