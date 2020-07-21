
/**
 * Created By Ravichandra S V.
 */

package com.github.githubrepo.examplemvvm.view;


import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import io.github.erikjhordanrey.people_mvvm.R;

import io.github.erikjhordanrey.people_mvvm.databinding.HomeActivityBindingImpl;

import com.github.githubrepo.examplemvvm.model.repoModel.RequestAdd;
import com.github.githubrepo.examplemvvm.viewmodel.GitRepoViewModel;

import java.util.Observable;
import java.util.Observer;

public class HomeGitActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private GitRepoViewModel gitRepoViewModel;

    private HomeActivityBindingImpl binding;
    EditText name,RepoName;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.recyclerRepo);
        setupObserver(gitRepoViewModel);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        gitRepoViewModel = new GitRepoViewModel(this);
        binding.setMainViewModel(gitRepoViewModel);
        name=findViewById(R.id.edit_name);
        RepoName=findViewById(R.id.edit_repo);
        submit=findViewById(R.id.button_submit);
        submit.setOnClickListener(this);
    }

    private void setupListPeopleView(RecyclerView recyclerPeople) {
        HomeGitAdapter adapter = new HomeGitAdapter();
        recyclerPeople.setAdapter(adapter);
        recyclerPeople.setHasFixedSize(true);
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gitRepoViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof GitRepoViewModel) {
            HomeGitAdapter homeGitAdapter = (HomeGitAdapter) binding.recyclerRepo.getAdapter();
            GitRepoViewModel gitRepoViewModel = (GitRepoViewModel) observable;
            if (homeGitAdapter != null) {
                homeGitAdapter.setPeopleList(gitRepoViewModel.getRepositoryList());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_submit:
                RequestAdd requestAdd=new RequestAdd();
                requestAdd.setName(name.getText().toString());
                requestAdd.setDescription(RepoName.getText().toString());
                if (name.getText().toString().length() > 0 && RepoName.getText().toString().length() > 0) {
                    gitRepoViewModel.addRepo(requestAdd);
                }
                break;
        }
    }
}
