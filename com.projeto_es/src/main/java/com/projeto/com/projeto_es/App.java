package com.projeto.com.projeto_es;
import java.util.List;
import twitter4j.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String str = "ISCTE";
    	Twitter twitter = new TwitterFactory().getInstance();
    	try {
    	Query query = new Query(str);
    	QueryResult result;
    	do {
    	result = twitter.search(query);
    	List<Status> tweets = result.getTweets();
    	for (Status tweet : tweets) {
    	System.out.println("@" + tweet.getUser().getScreenName()
    	+ " - " + tweet.getText());
    	}
    	} while ((query = result.nextQuery()) != null);
    	System.exit(0);
    	} catch (TwitterException te) {
    	te.printStackTrace();
    	System.out.println("Failed to search tweets: " + te.getMessage());
    	System.exit(-1);
    	}

    }
}
