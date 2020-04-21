package pendu;

public class Joueur
{

	       private String nom;
	       private int score;
	       private int nbre_erreur;
	       public Joueur(String name)
	       {
	    	   if (name.exist(archive.txt))
	    		 score.upload(archive.txt);
	    	   else
	    		   score=0;
	    	   nom=name;
	    	   nbre_erreur=0;
	       }
	       public void CalculScore(int x)
	       {   
	    	   score+=x;
	       }
	       public String getNom()
	       {
	    	   return(nom);
	       }
	       public int getscore()
	       {
	    	   return(score);
	       }
	       public int getNbre_erreur()
	       {
	    	   return(nbre_erreur);
	       }
	       public void setNbre_erreur(int n)
	       {
	    	   nbre_erreur=n;
	       }


	}



