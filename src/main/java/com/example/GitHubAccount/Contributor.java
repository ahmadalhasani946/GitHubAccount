package com.example.GitHubAccount;

public class Contributor implements Comparable<Contributor>{
    private String repositoryName, userName;
    private int contributionQuantity, followersQuantity;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getContributionQuantity() {
        return contributionQuantity;
    }

    public void setContributionQuantity(int contributionQuantity) {
        this.contributionQuantity = contributionQuantity;
    }

    public int getFollowersQuantity() {
        return followersQuantity;
    }

    public void setFollowersQuantity(int followersQuantity) {
        this.followersQuantity = followersQuantity;
    }

    @Override
    public int compareTo(Contributor other) {
        return other.getContributionQuantity() - getContributionQuantity();
    }

    public static String titles(){
        return "repo" + "," + "username" + "," + "contributions" + "," + "followers";
    }

    public String getInfo(){
        return  getRepositoryName() + "," + getUserName() + "," + getContributionQuantity() + "," + getFollowersQuantity();
    }
}


