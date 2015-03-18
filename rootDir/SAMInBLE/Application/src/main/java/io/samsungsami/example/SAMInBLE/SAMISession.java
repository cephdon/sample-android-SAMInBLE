/*
 * Copyright (C) 2015 Samsung Electronics Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.samsungsami.example.SAMInBLE;

import android.os.Looper;
import android.util.Log;

import io.samsungsami.api.DevicesApi;
import io.samsungsami.api.MessagesApi;
import io.samsungsami.api.UsersApi;

public class SAMISession {
    private static final String TAG = SAMISession.class.getSimpleName();

    public static final String SAMIHUB_BASE_PATH = "https://api.samsungsami.io/v1.1";
    public static final String SAMI_AUTH_BASE_URL = "https://accounts.samsungsami.io";
    public static final String CLIENT_ID = "Your YOUR CLIENT APP ID";
    public static final String REDIRECT_URL = "android-app://redirect";

    // SAMI device type id used by this app
    // device name: "SAMI Example Heart Rate Tracker"
    // As a hacker, get the device type id using the following ways
    //   -- login to https://api-console.samsungsami.io/sami
    //   -- Click "Get Device Types" api
    //   -- Fill in device name as above
    //   -- Click "Try it"
    //   -- The device type id is "id" field in the response body
    // You should be able to get this device type id programmatically.
    // Consult https://blog.samsungsami.io/mobile/development/2015/02/09/developing-with-sami-part-2.html#get-the-withings-device-info
    //
    public static final String DEVICE_TYPE_ID_HEART_RATE_TRACKER = "dtaeaf898b4db9418baab77563b7ea2254";

    private static SAMISession instance;

    private UsersApi mUsersApi = null;
    private DevicesApi mDevicesApi = null;
    private MessagesApi mMessagesApi = null;

    private String mAccessToken = null;
    private String mUserId = null;
    private String mDeviceId = null;

    public static SAMISession getInstance() {
        if (instance == null) {
            instance = new SAMISession();
        }
        return instance;
    }

    private SAMISession() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "Constructor is not called in UI thread ");
        }
    }

    public void setAccessToken(String token) {
        if (token == null || token.length() <= 0) {
            Log.e(TAG, "Attempt to set a invalid token");
            mAccessToken = null;
            return;
        }
        mAccessToken = token;
    }

    public void setupSamiApis() {
        // Invoke the appropriate API
        mUsersApi = new UsersApi();
        mUsersApi.setBasePath(SAMIHUB_BASE_PATH);
        mUsersApi.addHeader("Authorization", "bearer " + mAccessToken);

        mDevicesApi = new DevicesApi();
        mDevicesApi.setBasePath(SAMIHUB_BASE_PATH);
        mDevicesApi.addHeader("Authorization", "bearer " + mAccessToken);

        mMessagesApi = new MessagesApi();
        mMessagesApi.setBasePath(SAMIHUB_BASE_PATH);
        mMessagesApi.addHeader("Authorization", "bearer " + mAccessToken);
    }

    public void logout() {
        reset();
    }

    public UsersApi getUsersApi() {
        return mUsersApi;
    }

    public DevicesApi getDevicesApi() {
        return mDevicesApi;
    }

    public MessagesApi getMessagesApi() {
        return mMessagesApi;
    }

    public void setUserId(String uid) {
        if (uid == null || uid.length() <= 0) {
            Log.w(TAG, "setUserId() get null uid");
        }
        mUserId = uid;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setDeviceId(String did) {
        if (did == null || did.length() <= 0) {
            Log.w(TAG, "setDeviceId() get null did");
        }
        mDeviceId = did;
    }

    public String getDeviceId() {
        return mDeviceId;
    }

    public void reset() {
        mUsersApi = null;
        mDevicesApi = null;
        mMessagesApi = null;

        mAccessToken = null;
        mUserId = null;
        mDeviceId = null;
    }
}
