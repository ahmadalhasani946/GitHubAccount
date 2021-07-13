package com.example.GitHubAccount;

public class Repository implements Comparable<Repository>{
    private String name, htmlUrl, description, contributorsUrl;
    private int forksCount;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setHtml_url(String htmlUrl){
        this.htmlUrl = htmlUrl;
    }

    public String getHtml_url(){
        return this.htmlUrl;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setContributors_url(String contributorsUrl){
        this.contributorsUrl = contributorsUrl;
    }

    public String getContributors_url(){
        return this.contributorsUrl;
    }

    public void setForks_count(int forksCount){
        this.forksCount = forksCount;
    }

    public int getForks_count(){
        return this.forksCount;
    }

    @Override
    public int compareTo(Repository other) {
        return other.getForks_count() - getForks_count();
    }

    public static String titles(){
        return "repo" + "," + "forks" + "," + "url" + "," + "description";
    }

    public String getInfo(){
        return  getName() + "," + getForks_count() + "," + getHtml_url() + "," + getDescription();
    }
}
