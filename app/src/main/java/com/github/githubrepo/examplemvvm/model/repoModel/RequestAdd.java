package com.github.githubrepo.examplemvvm.model.repoModel;

public class RequestAdd{
	private boolean hasProjects;
	private boolean jsonMemberPrivate;
	private boolean hasWiki;
	private String name;
	private String description;
	private boolean hasIssues;
	private String homepage;

	public void setHasProjects(boolean hasProjects){
		this.hasProjects = hasProjects;
	}

	public boolean isHasProjects(){
		return hasProjects;
	}

	public void setJsonMemberPrivate(boolean jsonMemberPrivate){
		this.jsonMemberPrivate = jsonMemberPrivate;
	}

	public boolean isJsonMemberPrivate(){
		return jsonMemberPrivate;
	}

	public void setHasWiki(boolean hasWiki){
		this.hasWiki = hasWiki;
	}

	public boolean isHasWiki(){
		return hasWiki;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setHasIssues(boolean hasIssues){
		this.hasIssues = hasIssues;
	}

	public boolean isHasIssues(){
		return hasIssues;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	@Override
 	public String toString(){
		return 
			"RequestAdd{" + 
			"has_projects = '" + hasProjects + '\'' + 
			",private = '" + jsonMemberPrivate + '\'' + 
			",has_wiki = '" + hasWiki + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",has_issues = '" + hasIssues + '\'' + 
			",homepage = '" + homepage + '\'' + 
			"}";
		}
}
