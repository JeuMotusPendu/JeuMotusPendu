package Pendu;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Mot {
	//Utilisation de StringBuffer car ses objets sont modifiables
	protected StringBuffer mot,masq;
	//Constructeur:l'entier entrer en parametre indique la langue:français ou anglais
	public Mot(int n) throws FileNotFoundException
	{
		Scanner s;
		if (n==1)
		{
			s = new Scanner(new File("Data_fr.txt"));
		}
		else
		{
			s = new Scanner(new File("Data_ang.txt"));
		}
		ArrayList<String> list = new ArrayList<String>();
		//on met les mots du fichier .txt dans une liste
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		Random rand = new Random();
		mot=new StringBuffer("");
		masq= new StringBuffer("");
		//on choisit un mot au hasard de la liste
		mot.append(list.get(rand.nextInt(list.size())));
		for(int i=0;i<mot.length();i++) {
			masq.append("*");
		}
		
	}
	public Mot(StringBuffer m) {
		this.mot=m;
		masq= new StringBuffer("");
		for(int i=0;i<m.length();i++) {
			masq.append("*");
		}
	}
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
	public void setmasque(char c) {
		for(int i=0;i<mot.length();i++) {
			if(mot.charAt(i)==c) {masq.setCharAt(i,c);}
		}
	}
	public StringBuffer getmasque() {
		return(this.masq);
	}
	public int nbt() {
		return((mot.length())*2);
	}	
}