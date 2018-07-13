package com.greedygame.android.plugin;

import android.util.Log;

import com.greedygame.android.agent.GreedyGameAgent;
import com.greedygame.android.agent.PrivacyOptions;
import com.greedygame.android.core.campaign.CampaignStateListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GreedyGamePlugin extends CordovaPlugin implements CampaignStateListener {

    private static final String TAG = GreedyGamePlugin.class.getSimpleName();

    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static final String INIT = "init";
    public static final String GET_PATH = "getPath";
    public static final String ADMOB = "admob";
    public static final String MOPUB = "mopub";
    public static final String FACEBOOK = "facebook";
    public static final String NPA = "npa";
    public static final String GAME_ENGINE = "gameEngine";
    public static final String ENGINE_VERSION = "engineVersion";
    public static final String SEND_CRASH = "sendCrash";
    public static final String UNITS = "units";
    public static final String REFRESH = "refresh";
    public static final String SHOW_UII = "showUii";

    private GreedyGameAgent mGreedyGameAgent;

    private CallbackContext mCallbackContext;

    private boolean mAdmob;
    private boolean mMopub;
    private boolean mFacebook;
    private boolean mSendCrash;
    private boolean mNpa;
    private String mGameEngine;
    private String mEngineVersion;
    private List<String> mUnits = new CopyOnWriteArrayList<>();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing Plugin");
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "Action called");

        switch (action) {
            case INIT:
                mCallbackContext = callbackContext;
                init();
                return true;
            case GET_PATH:
                getPath(args.getString(0), callbackContext);
                return true;
            case UNITS:
                units(args.getJSONArray(0));
                return true;
            case ADMOB:
                mAdmob = args.getBoolean(0);
                return true;
            case MOPUB:
                mMopub = args.getBoolean(0);
                return true;
            case FACEBOOK:
                mFacebook = args.getBoolean(0);
                return true;
            case NPA:
                mNpa = args.getBoolean(0);
                return true;
            case SEND_CRASH:
                mSendCrash = args.getBoolean(0);
                return true;
            case REFRESH:
                refresh();
                return true;
            case GAME_ENGINE:
                mGameEngine = args.getString(0);
                return true;
            case ENGINE_VERSION:
                mEngineVersion = args.getString(0);
                return true;
            case SHOW_UII:
                showUii(args.getString(0));
                return true;
        }

        return false;
    }

    private void showUii(String unitId) {
        mGreedyGameAgent.showUII(unitId);
    }

    private void units(JSONArray jsonArray) {
        Log.d(TAG, "Units: " + jsonArray.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            mUnits.add(jsonArray.optString(i));
        }
    }

    private void init() {
        Log.d(TAG, "Inside Init");
        mGreedyGameAgent = new GreedyGameAgent.Builder(cordova.getActivity())
                .withAgentListener(this)
                .enableAdmob(mAdmob)
                .enableMopub(mMopub)
                .enableFacebook(mFacebook)
                .enableCrash(mSendCrash)
                .gameEngine(mGameEngine)
                .engineVersion(mEngineVersion)
                .addUnitList(mUnits)
                .build();
        PrivacyOptions privacyOptions = new PrivacyOptions();
        privacyOptions.setGgNpa(mNpa);
        mGreedyGameAgent.withPrivacyOptions(privacyOptions);
        mGreedyGameAgent.init();
    }

    private void refresh() {
        mGreedyGameAgent.startEventRefresh();
    }

    private void getPath(String unitId, CallbackContext callbackContext) {
        callbackContext.success(mGreedyGameAgent.getPath(unitId));
    }

    @Override
    public void onFound() {

    }

    @Override
    public void onUnavailable() {

    }

    @Override
    public void onAvailable(String s) {
        Log.d(TAG, "Received in Plugin: Available");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(STATUS, SUCCESS);
            jsonObject.put(DATA, s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jsonObject);
        pluginResult.setKeepCallback(true);
        mCallbackContext.sendPluginResult(pluginResult);
        //mCallbackContext.success(s);
    }

    @Override
    public void onError(String s) {
        Log.d(TAG, "Received in Plugin: UnAvailable");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(STATUS, FAILURE);
            jsonObject.put(DATA, s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jsonObject);
        pluginResult.setKeepCallback(true);
        mCallbackContext.sendPluginResult(pluginResult);
        //mCallbackContext.error(s);
    }
}

