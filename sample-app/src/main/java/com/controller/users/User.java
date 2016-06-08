package com.controller.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

public class User {

    private String id;
    private String email;
    private String password;
    private String displayName;
    private String facebook;
    private String google;
    private String linkedin;
    private String github;
    private String foursquare;
    private String twitter;

    public enum Provider {
        FACEBOOK("facebook"), GOOGLE("google"), LINKEDIN("linkedin"), GITHUB("github"), FOURSQUARE(
                "foursquare"), TWITTER("twitter");

        String name;

        Provider(final String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String capitalize() {
            return StringUtils.capitalize(this.name);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getGoogle() {
        return google;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public String getFoursquare() {
        return foursquare;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setDisplayName(final String name) {
        this.displayName = name;
    }

    public void setProviderId(final Provider provider, final String value) {
        switch (provider) {
            case FACEBOOK:
                this.facebook = value;
                break;
            case GOOGLE:
                this.google = value;
                break;
            case LINKEDIN:
                this.linkedin = value;
                break;
            case GITHUB:
                this.github = value;
                break;
            case FOURSQUARE:
                this.facebook = value;
                break;
            case TWITTER:
                this.twitter = value;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @JsonIgnore
    public int getSignInMethodCount() throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException {
        int count = 0;

        if (this.getPassword() != null) {
            count++;
        }

        for (final Provider p : Provider.values()) {
            if (this.getClass().getDeclaredField(p.name).get(this) != null) {
                count++;
            }
        }

        return count;
    }

}
