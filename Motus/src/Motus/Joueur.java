package Motus;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class Joueur
{
	       private String nom;
	       private int score;
               //constructeur
	
	       public Joueur(String name) throws IOException
	       {
	    	    nom=name;
                    //Creation d'un fichier dans lequel on va enregistrer les joueurs ainsi que leurs scores
	    	    File f=new File("archive.txt");
	    	    f.createNewFile();
	    	    FileReader fichier = new FileReader(f);
	    		BufferedReader in = new BufferedReader(fichier);
	    		String line;
	    		boolean found=false;
	    		while((line = in.readLine())!= null && found==false)
	    		{
	    			String [] joueur;
	    			joueur = line.split(" ");
                                //si le joueur actuel existe dans le fichier,on recupere son ancien score
	    			if ( joueur[0].equals(getNom()) )
	    			{
	    				score =Integer.valueOf(joueur[1]);
	    				found=true;
	    			}
	    		}
	    		in.close();
	    		if( found==false )
	    			score=0;
	       }
	       public void CalculScore(int x)
	       {
	    	   score+=x;
	       }
	       public String getNom()
	       {
	    	   return(nom);
	       }
	       public int getScore()
	       {
	    	   return(score);
	       }
	       public void enregistrerJoueur() throws IOException
	       {
	    	   File f = new File("archive.txt");
	    	   FileReader fichier = new FileReader(f);
	    	   BufferedReader in = new BufferedReader(fichier);
	    	   String line;
	    	   boolean found=false;
	    	   while(((line = in.readLine())!= null) && (found==false))
	    	   {
	    			String [] joueur;
	    			joueur = line.split(" ");
                                 //le score d'un joueur sera actualisé dans le fichier nouveauarchive.txt 
	    			if ( joueur[0].equals(getNom()) )
	    			{
	    				File f2 = new File("nouveauarchive.txt");
	    				f2.createNewFile();
	    				BufferedReader fichier1 = new BufferedReader(new FileReader(f));
	    				BufferedWriter fichierW = new BufferedWriter(new FileWriter(f2,true));
	    				String str;
	    				String [] joueur1;
	    				str = fichier1.readLine();
	    				
	    				while (str!=null)
	    				{
	    					joueur1=str.split(" ");
	    					if(joueur1[0].equals(this.getNom()))
	    						str=str.replace(joueur1[1],String.valueOf(this.getScore()));
	    					fichierW.write(str+"\n");
	    					fichierW.flush();
	    					str = fichier1.readLine( );
	    				}
	    				fichierW.close();
	    				fichier1.close();
	    				found=true;
	    			}
	    		}
	    	    fichier.close();
	    		in.close();
                        //suppression du fichier archive.txt et renommage du fichier nouveauarchive.txt qui contient le score actualisé
	    		if (found==true)
	    		{
	    			f.delete();
	    			File f2=new File("nouveauarchive.txt");
    				f2.renameTo(f);
	    		}
	    		if( found==false )
	    		{
	    			BufferedWriter bw = new BufferedWriter(new FileWriter("archive.txt",true));
	    			bw.write(nom+" "+score);
	    			bw.newLine();
	    			bw.close();
	    		}
	       }
}
