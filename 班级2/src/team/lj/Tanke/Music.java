package team.lj.Tanke;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.applet.*;


public class Music {
	public AudioClip cilp;	
	public AudioClip cilpA;
	public AudioClip cilpB;
	public AudioClip cilpCl;
	public AudioClip cilpC;
	public AudioClip cilpE;
	
	File main = new File ("src/Image/UI/main.wav");
	File aFire = new File ("src/Image/UI/aFire.wav");
	File bFire = new File ("src/Image/UI/bFire.wav");
	File cFire = new File ("src/Image/UI/cFire.wav");
	File game = new File ("src/Image/UI/game.wav");
    File click = new File ("src/Image/UI/click.wav");
	File lose = new File ("src/Image/UI/lose.wav");
	File win = new File ("src/Image/UI/win.wav");
	File explode =new File ("src/Image/UI/explode.wav");
	
	URI uri;
    URI uriA;
    URI uriB;
    URI uriCl;
    
    URL url = null;
    
	int timesOfAFire=0;
	int timesOfBFire=0;
	int timesOfCFire=0;
	int timesOfExplode=0;
	
	
	
	public void musicRun (String music){
		
		
		if(music=="main"){
		uri=main.toURI();
		
		try {
			url = uri.toURL();
		} catch (MalformedURLException e) {
		
		}
	    cilp = Applet.newAudioClip(url);
	    cilp.loop();
		}
		
		if(music=="lose"){
			uri=lose.toURI();
			
			try {
				url = uri.toURL();
			} catch (MalformedURLException e) {
			
			}
		    cilp = Applet.newAudioClip(url);
		    cilp.loop();
			}
		if(music=="win"){
			uri=win.toURI();
			
			try {
				url = uri.toURL();
			} catch (MalformedURLException e) {
			
			}
		    cilp = Applet.newAudioClip(url);
		    cilp.loop();
			}
		
		if(music=="click"){
		uri=click.toURI();
		
		try {
			url = uri.toURL();
		} catch (MalformedURLException e) {
		
		}
	    cilpCl = Applet.newAudioClip(url);
	    cilpCl.play();
		}
		 if (music=="aFire"){
			if(timesOfAFire<1){
		uriA=aFire.toURI();
		
		try {
			url = uriA.toURL();
			 cilpA = Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
		
		}
			

	  
			}
		   
			  cilpA.play();
	   
		
		timesOfAFire++;
		}
		 
		if (music=="bFire"){
			if(timesOfBFire<1){
			uriB=bFire.toURI();
			
			try {
				url = uriB.toURL();
				cilpB = Applet.newAudioClip(url);
			} catch (MalformedURLException e) {
			
			}
			}
		    cilpB.play();
			timesOfBFire++;
			}
		 if (music=="explode"){
				if(timesOfExplode<1){
			uriA=explode.toURI();
			
			try {
				url = uriA.toURL();
				 cilpA = Applet.newAudioClip(url);
			} catch (MalformedURLException e) {
			
			}
				

		  
				}
			   
				  cilpA.play();
		   
			
			
			timesOfExplode++;
			}
		 if (music=="cFire"){
				if(timesOfCFire<1){
			uriA=aFire.toURI();
			
			try {
				url = cFire.toURL();
				 cilpC = Applet.newAudioClip(url);
			} catch (MalformedURLException e) {
			
			}
				

		  
				}
			   
				  cilpC.play();
		   
			
			timesOfCFire++;
			}
		if (music=="game"){
			URI uri=game.toURI();
			URL url = null;
			try {
				url = uri.toURL();
			} catch (MalformedURLException e) {
				
			}
			 cilp = Applet.newAudioClip(url);
			    cilp.loop();
			}
		
		}
	
	    	
			
			
		   
		
			
	


	
}
