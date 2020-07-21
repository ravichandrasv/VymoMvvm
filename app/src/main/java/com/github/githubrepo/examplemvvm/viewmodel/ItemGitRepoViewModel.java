
/**
 * Created By Ravichandra S V.
 */

package com.github.githubrepo.examplemvvm.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.github.githubrepo.examplemvvm.model.repoModel.Repository;

public class ItemGitRepoViewModel extends BaseObservable {

    private final Context context;
    private Repository gitRepository;

    public ItemGitRepoViewModel(Context context, Repository people) {
        this.context = context;
        this.gitRepository = people;
    }

    public String getFullName() {
        return gitRepository.getName();
    }

    /*public String getCell() {
        return repository.getFullName();
    }*/

    public String getCloneName() {
        return gitRepository.getFullName();
    }



    public void setGitHubrepository(Repository repository) {
        this.gitRepository = repository;
        notifyChange();
    }
}
