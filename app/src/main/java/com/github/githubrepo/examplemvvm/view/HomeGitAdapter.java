
/**
 * Created By Ravichandra S V.
 */

package com.github.githubrepo.examplemvvm.view;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.github.erikjhordanrey.people_mvvm.R;

import io.github.erikjhordanrey.people_mvvm.databinding.ItemGitrepoBinding;

import com.github.githubrepo.examplemvvm.model.repoModel.Repository;
import com.github.githubrepo.examplemvvm.viewmodel.ItemGitRepoViewModel;

import java.util.Collections;
import java.util.List;

public class HomeGitAdapter extends RecyclerView.Adapter<HomeGitAdapter.GitHomeAdapterViewHolder> {

    private List<Repository> gitRepoList;

    HomeGitAdapter() {
        this.gitRepoList = Collections.emptyList();
    }

    @NonNull
    @Override
    public GitHomeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGitrepoBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gitrepo, parent, false);
        return new GitHomeAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(GitHomeAdapterViewHolder holder, int position) {
        holder.bindGitRepo(gitRepoList.get(position));
    }

    @Override
    public int getItemCount() {
        return gitRepoList.size();
    }

    void setPeopleList(List<Repository> peopleList) {
        this.gitRepoList = peopleList;
        notifyDataSetChanged();
    }

    static class GitHomeAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemGitrepoBinding binding;

        GitHomeAdapterViewHolder(ItemGitrepoBinding binding) {
            super(binding.itemPeople);
            this.binding = binding;
        }

        void bindGitRepo(Repository people) {
            if (binding.getGitRepoViewModel() == null) {
                binding.setGitRepoViewModel(new ItemGitRepoViewModel(itemView.getContext(), people));
            } else {
                binding.getGitRepoViewModel().setGitHubrepository(people);
            }
        }
    }
}
