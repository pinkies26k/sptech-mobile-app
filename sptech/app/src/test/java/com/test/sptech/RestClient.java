/*
 * Copyright (C) 2015. Victor Apoyan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.sptech;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 *
 */
public final class RestClient {

    private static IRestService mRestService = null;
    private final static String TAG  = RestClient.class.getSimpleName();

    public static IRestService getClient() {
        if(mRestService == null) {
            final OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new FakeInterceptor());

            final Retrofit retrofit = new Retrofit.Builder()
                            // Using custom Jackson Converter to parse JSON
                            // Add dependencies:
                            // com.squareup.retrofit:converter-jackson:2.0.0-beta2
                    .addConverterFactory(JacksonConverterFactory.create())
                            // Endpoint
                    .baseUrl(IRestService.ENDPOINT)
                    .client(client)
                    .build();

            mRestService = retrofit.create(IRestService.class);
        }
        return mRestService;
    }

    public void testGetClient(){
//        Call<Teacher> teacherCall = RestClient.getClient().getTeacherById("1");
//        teacherCall.enqueue(new Callback<Teacher>() {
//            @Override
//            public void onResponse(Response<Teacher> response, Retrofit retrofit) {
//                Teacher teacher = response.body();
//                Log.d(TAG, teacher.toString());
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "Failure " + t.getMessage());
//            }
//        });
    }
}