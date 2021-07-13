package com.example.GitHubAccount;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Github {
    public String account;

    public Github(String account){
        this.account = account;
    }

    public JSONArray getJsonArray(String link){
        String responseBody = Parser.getResponse(link);
        return new JSONArray(responseBody);
    }

    public Repository[] getMostForked(int forks){
        String urlString = "https://api.github.com/users/" + this.account + "/repos";
        JSONArray repos = getJsonArray(urlString);

        Repository[] sortedRepos = GetRepositoriesForForked(repos);

        if(forks > sortedRepos.length){
            return sortedRepos;
        }
        else {
            Repository[] repositories = new Repository[forks];
            System.arraycopy(sortedRepos, 0, repositories, 0, forks);
            return repositories;
        }
    }


    public Repository[] GetRepositoriesForForked(JSONArray repos){
        Repository[] repositories = new Repository[repos.length()];

        for(int i=0; i < repos.length(); i++){
            JSONObject repo = repos.getJSONObject(i);
            repositories[i] = new Repository();
            repositories[i].setForks_count(repo.getInt("forks_count"));
            repositories[i].setName(repo.getString("name"));
            repositories[i].setHtml_url(repo.getString("html_url"));
            if(repo.get("description") != null){
                repositories[i].setDescription(repo.get("description").toString());
            }
            else {
                repositories[i].setDescription("No Description");
            }
            repositories[i].setContributors_url(repo.getString("contributors_url"));
        }

        Arrays.sort(repositories);

        return repositories;
    }

    public List<Contributor> getMostContributers(Repository[] repositories, int Contributers){
        List<Contributor> contributorList = new ArrayList<Contributor>();

        for (Repository value : repositories) {
            JSONArray repository = getJsonArray(value.getContributors_url());

            getContributors(contributorList, repository, Contributers, value.getName());
        }

        return contributorList;
    }

    public void getContributors(List<Contributor> contributorList, JSONArray repository, int contributorsNumber, String RepositoryName){
        Contributor[] contributorsArray = new Contributor[repository.length()];

        for(int i=0; i < repository.length(); i++){
            JSONObject repo = repository.getJSONObject(i);
            contributorsArray[i] = new Contributor();

            contributorsArray[i].setRepositoryName(RepositoryName);
            contributorsArray[i].setUserName(repo.getString("login"));
            contributorsArray[i].setContributionQuantity(repo.getInt("contributions"));

            JSONArray followers = getJsonArray(repo.getString("followers_url"));
            contributorsArray[i].setFollowersQuantity(followers.length());
        }

        Arrays.sort(contributorsArray);



        pushTheTopContributors(contributorsArray, contributorList, contributorsNumber);
    }

    public void pushTheTopContributors(Contributor[] contributors, List<Contributor> contributorList, int contributorsNumber){
        if(contributorsNumber > contributors.length){
            contributorsNumber = contributors.length;
        }
        contributorList.addAll(Arrays.asList(contributors).subList(0, contributorsNumber));
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
