package com.automation.pilot.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.pilot.pages.SearchPage;
import com.automation.pilot.utilities.TestBaseClass;

public class TestGoogleSearch extends TestBaseClass{
    @Test
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage = new SearchPage(getDriver());
//        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();

        Assert.assertTrue(size > 0);
    }

}
