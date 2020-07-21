
/**
 * Created By Ravichandra S V.
 */
package com.github.githubrepo.examplemvvm;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.github.githubrepo.examplemvvm.data.GitFactory;
import com.github.githubrepo.examplemvvm.data.GitApiService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class Application extends MultiDexApplication {

    private GitApiService gitApiService;
    private Scheduler scheduler;

    private static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public static Application create(Context context) {
        return Application.get(context);
    }

    public GitApiService getPeopleService() {
        if (gitApiService == null) {
            gitApiService = GitFactory.create();
        }

        return gitApiService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
