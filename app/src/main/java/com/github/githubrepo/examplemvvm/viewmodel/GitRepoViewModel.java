
/**
 * Created By Ravichandra S V.
 */

package com.github.githubrepo.examplemvvm.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;

import android.view.View;
import android.widget.Toast;

import com.github.githubrepo.examplemvvm.Application;
import com.github.githubrepo.examplemvvm.data.GitApiService;
import com.github.githubrepo.examplemvvm.model.repoModel.Repository;
import com.github.githubrepo.examplemvvm.model.repoModel.RequestAdd;
import com.github.githubrepo.examplemvvm.utils.Constants;


import io.github.erikjhordanrey.people_mvvm.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GitRepoViewModel extends Observable {

    public ObservableInt gitRepoProgress;
    public ObservableInt gitRepoRecyclerView;
    public ObservableInt gitRepoLabel;
    public ObservableField<String> messageLabel;

    public ObservableInt gitName;
    public ObservableInt gitRepoName;

    private List<Repository> repositoryList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public GitRepoViewModel(@NonNull Context context) {

        this.context = context;
        this.repositoryList = new ArrayList<>();
        gitRepoProgress = new ObservableInt(View.GONE);
        gitRepoRecyclerView = new ObservableInt(View.GONE);
        gitRepoLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }


    //It is "public" to show an example of test
    public void initializeViews() {
        gitRepoLabel.set(View.GONE);
        gitRepoRecyclerView.set(View.GONE);
        gitRepoProgress.set(View.VISIBLE);
    }

    // NOTE: This method can return the rx observer and just subscribe to it in the activity or fragment,
    // an Activity or Fragment needn't to implement from the Observer java class
    // (it was my first approach to avoid RX in UI now we can use LiveData instead)
    private void fetchPeopleList() {

        Application application = Application.create(context);
        GitApiService gitApiService = application.getPeopleService();



        Disposable disposable = gitApiService.getrepository(Constants.getMap())
                .subscribeOn(application.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Repository>>() {
                    @Override
                    public void accept(List<Repository> peopleResponse) {

                        changePeopleDataSet(peopleResponse);
                        gitRepoProgress.set(View.GONE);
                        gitRepoLabel.set(View.GONE);
                        gitRepoRecyclerView.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        messageLabel.set(context.getString(R.string.error_loading_people));
                        gitRepoProgress.set(View.GONE);
                        gitRepoLabel.set(View.VISIBLE);
                        gitRepoRecyclerView.set(View.GONE);
                        throwable.printStackTrace();
                    }
                });

        compositeDisposable.add(disposable);
    }

    public void addRepo(RequestAdd add) {

        Application application = Application.create(context);
        GitApiService gitApiService = application.getPeopleService();



        Disposable disposable = gitApiService.addRepo(Constants.getMap(),add)
                .subscribeOn(application.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Repository>>() {
                    @Override
                    public void accept(List<Repository> peopleResponse) {
                        Toast.makeText(context,"Added",Toast.LENGTH_LONG).show();
                        gitRepoProgress.set(View.GONE);
                        gitRepoLabel.set(View.GONE);
                        gitRepoRecyclerView.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        messageLabel.set(context.getString(R.string.error_loading_people));
                        gitRepoProgress.set(View.GONE);
                        gitRepoLabel.set(View.VISIBLE);
                        gitRepoRecyclerView.set(View.GONE);
                        throwable.printStackTrace();
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changePeopleDataSet(List<Repository> repoList) {
        if (repositoryList.size()==0) {
            repositoryList.addAll(repoList);
        }
        else {
            repositoryList.clear();
            repositoryList.addAll(repoList);
        }
        setChanged();
        notifyObservers();
    }

    public List<Repository> getRepositoryList() {
        return repositoryList;
    }

    public void reset() {
        compositeDisposable.dispose();
        compositeDisposable = null;
        context = null;
    }
}
