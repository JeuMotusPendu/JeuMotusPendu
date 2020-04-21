package ca;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Mot {
	/* on a besion de StringBuffer pour parcourir(methode comparer)
	et pour inserer les lettres existantes dans leurs places (methode setmasque)*/
	protected StringBuffer mot,masq;
	//Constructeur1 pour un seul joueur contre la machine
	public Mot() throws FileNotFoundException {
		Scanner s = new Scanner(new File("Data.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		Random rand = new Random();
		//choisir un mot quelconque du fichier Data.txt
		mot.append(list.get(rand.nextInt(list.size())));
		for(int i=0;i<mot.length();i++) {
			masq.append("*");
		}
		
	}
	//constructeur2 pour deux joueur
	public Mot(StringBuffer m) {
		this.mot=m;
		for(int i=0;i<m.length();i++) {
			masq.append("*");
		}
		System.out.println("masque:"+masq);
	}
	//retourne true si le caractere existe dans le mot,false sinon
	public boolean comparer(char c) {
		int res=0,i=0;
		for(i=0;i<mot.length();i++) {
			if(mot.charAt(i)==c) {
				res++;
			}
		}
		return(res!=0);
	}
	public StringBuffer getmot() {
		return(this.mot);
	}
	/*la methode setmasque remplace toute les occurrences du caractere entrer en parametre dans le mot masqué 
	 puis l'affiche */
	public void setmasque(char c) {
		for(int i=0;i<mot.length();i++) {
			if(mot.charAt(i)==c) {masq.setCharAt(i,c);}
		}
		System.out.println(this.masq);
	}
	public StringBuffer getmasque() {
		return(this.masq);
	}
	//retourne le nombre de tentative pour chaque mot
	public int nbt() {
		return((mot.length())*3);
	}	
}
