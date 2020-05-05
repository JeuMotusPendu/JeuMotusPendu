package Pendu;

import java.util.Scanner;
import java.io.*;
public class Jeu 
{
	protected int difficulte;
	protected int mode;
	public Jeu()
	{
		difficulte=0;
		mode=0;
	}
	public Jeu(int a,int b)
	{
		difficulte=a;
		mode=b;
	}
	public void set_d(int d)
	{
		difficulte=d;
	}
	public void set_m(int m)
	{
		mode=m;
	}
	public int get_m()
	{
		return mode;
	}
	public int get_d()
	{
		return difficulte;
	}
	public int fen1(Scanner sc)
	{
		System.out.println("Difficulté:");
		System.out.println("1-Facile");
		System.out.println("2-Difficile");
		System.out.println("3-Quitter");
		int n=sc.nextInt();
		sc.nextLine();
		if (n==3)
			System.exit(0);
		for (int i=0;i<20;i++)
		{
			System.out.println("\n");
		}
		return n;
	}
	public int fen2(Scanner sc)
	{
		System.out.println("Options:");
		System.out.println("1-1 Joueur");
		System.out.println("2-2 Joueur");
		System.out.println("3-Contre la montre");
		System.out.println("4-Retour");
		int n=sc.nextInt();
		sc.nextLine();
		for (int i=0;i<20;i++)
		{
			System.out.println("\n");
		}
		return n;		
	}
	public void jouer1(Joueur j,Scanner sc) throws IOException
	{
		if (mode == 1)//mode un joueur
		{
			//intialisation des paramètres
			System.out.println("Choisir la langue du mot à deviner:");
			System.out.println("1-Français");
			System.out.println("2-Anglais");
			int n=sc.nextInt();
			sc.nextLine();
			for (int i=0;i<20;i++)
			{
				System.out.println("\n");
			}
			Mot m = new Mot(n);
			int nbrt =0;
			int nbree = 0;
			//intialisation du masque en cas de difficulé facile
			StringBuffer s=m.getmot();
			if (difficulte==1)
			{
				m.setmasque(s.charAt(0));
				m.setmasque(s.charAt(s.length()-1));
			}
			//déroulement du jeu
			System.out.println("Vous avez "+m.nbt()+" tentatives");
			System.out.println("le mot a deviner est: "+m.getmasque());
			boolean a=false;
			while((!a)&&(nbrt<m.nbt()))
			{
				nbrt++;
				System.out.println("Donnez un carctère: ");
				char c=sc.next().charAt(0);
				if (m.comparer(c))
				{
					m.setmasque(c);
					System.out.println(m.getmasque());
				}
				else
				{
					nbree++;
					System.out.println(m.getmasque());
				}
				a=m.getmot().toString().equals(m.getmasque().toString());
			}
			if (nbree<2)
			{
				j.CalculScore(4);
				System.out.println("Felicitation! Votre score augmente par 4 pour devenir: "+j.getScore());
			}
			else if ((1<nbree)&&(nbree<4))
			{
				j.CalculScore(3);
				System.out.println("Felicitation! Votre score augmente par 3 pour devenir: "+j.getScore());
			}
			else if ((3<nbree)&&(nbree<6))
			{
				j.CalculScore(2);
				System.out.println("Felicitation! Votre score augmente par 2 pour devenir: "+j.getScore());
			}
			else if ((nbree==6))
			{
				j.CalculScore(1);
				System.out.println("Felicitation! Votre score augmente par 1 pour devenir: "+j.getScore());
			}
			else if ((6<nbree)&&(nbree<=10))
			{
				System.out.println("Désolé vous gagnez 0 points votre score reste: "+j.getScore());
			}
			else
			{
				System.out.println("Désolé vous avez perdu!");
				System.out.println("Le mot recherché était: "+s);
			}
			j.enregistrerJoueur();
		}
		if (this.mode == 3)//mode contre la montre
		{
			//intialisation des paramètres
			System.out.println("Choisir la langue du mot à deviner:");
			System.out.println("1-Français");
			System.out.println("2-Anglais");
			int n=sc.nextInt();
			sc.nextLine();
			for (int i=0;i<20;i++)
			{
				System.out.println("\n");
			}
			Mot m = new Mot(n);
			int nbrt =0;
			int nbree = 0;
			System.out.println("Vous avez "+m.nbt()+" tentatives");
			//intialisation du masque en cas de difficulé facile
			StringBuffer s=m.getmot();
			if (difficulte==1)
			{
				m.setmasque(s.charAt(0));
				m.setmasque(s.charAt(s.length()-1));
			}
			//deroulement du jeu
			System.out.println("Vous avez une minute pour deviner le mot:");
			long temps=0;//intialisation du compteur du temps
			long startTime =System.currentTimeMillis();//commencement
			boolean a=false;
			System.out.println("le mot a deviner est: "+m.getmasque());
			while((!a)&&(nbrt<m.nbt())&&(temps<60000))
			{
				nbrt++;
				long ts=(60000-temps)/1000;
				System.out.println("Il vous reste "+ts+" Secondes");
				System.out.println("Donnez un carctère: ");
				char c= sc.next().charAt(0);
				if (m.comparer(c))
				{
					m.setmasque(c);
					System.out.println(m.getmasque());
				}
				else
				{
					nbree++;
					System.out.println(m.getmasque());
				}
				long endTime = System.currentTimeMillis();
				temps= endTime-startTime;//calcul du temps passé
				a=m.getmot().toString().equals(m.getmasque().toString());
			}
			if ((nbree<2)&&(temps<=60000))
			{
				j.CalculScore(4);
				System.out.println("Felicitation! Votre score augmente par 4 pour devenir: "+j.getScore());
			}
			else if ((1<nbree)&&(nbree<4)&&(temps<=60000))
			{
				j.CalculScore(3);
				System.out.println("Felicitation! Votre score augmente par 3 pour devenir: "+j.getScore());
			}
			else if ((3<nbree)&&(nbree<6)&&(temps<=60000))
			{
				j.CalculScore(2);
				System.out.println("Felicitation! Votre score augmente par 2 pour devenir: "+j.getScore());
			}
			else if ((nbree==6)&&(temps<=60000))
			{
				j.CalculScore(1);
				System.out.println("Felicitation! Votre score augmente par 1 pour devenir: "+j.getScore());
			}
			else if ((6<nbree)&&(nbree<=10)&&(temps<=60000))
			{
				System.out.println("Désolé vous gagnez 0 points votre score reste: "+j.getScore());
			}
			else
			{
				System.out.println("Désolé vous avez perdu!");
				System.out.println("Le mot recherché était: "+s);
			}
			j.enregistrerJoueur();
		}
	}
	public void jouer2(Joueur j,Joueur j2,Scanner sc) throws IOException
	{
		//intialisation des paramètres
		System.out.println("Joueur 1 donnez le mot à deviner:");
		String ch=sc.nextLine();
		StringBuffer s = new StringBuffer("");
		s.append(ch);
		for (int i=0;i<30;i++)
		{
			System.out.println("\n");
		}
		Mot m = new Mot(s);
		//intialisation du masque en cas de difficulé facile
		s=m.getmot();
		if (difficulte==1)
		{
			m.setmasque(s.charAt(0));
			m.setmasque(s.charAt(s.length()-1));
		}
		//déroulement du jeu
		int nbrt =0;
		int nbree = 0;
		System.out.println("Vous avez "+m.nbt()+" tentatives");
		System.out.println("le mot a deviner est: "+m.getmasque());
		boolean a=false;
		while((!a)&&(nbrt<m.nbt()))
		{
			nbrt++;
			System.out.println("Donnez un carctère: ");
			char c=sc.next().charAt(0);
			if (m.comparer(c))
			{
				m.setmasque(c);
				System.out.println(m.getmasque());
			}
			else
			{
				nbree++;
				System.out.println(m.getmasque());
			}
			a=m.getmot().toString().equals(m.getmasque().toString());
		}
		if (nbree<2)
		{
			j2.CalculScore(4);
			System.out.println("Joueur "+j2.getNom()+" votre score augmente par 4 pour devenir: "+j2.getScore());
			System.out.println("Joueur "+j.getNom()+" votre score reste unchangé et égale à: "+j.getScore());
		}
		else if ((1<nbree)&&(nbree<4))
		{
			j2.CalculScore(3);
			j.CalculScore(1);
			System.out.println("Joueur "+j2.getNom()+" votre score augmente par 3 pour devenir: "+j2.getScore());
			System.out.println("Joueur "+j.getNom()+" votre score augmente par 1 pour devenir: "+j.getScore());
		}
		else if ((3<nbree)&&(nbree<6))
		{
			j2.CalculScore(2);
			j.CalculScore(2);
			System.out.println("Joueur "+j2.getNom()+" votre score augmente par 2 pour devenir: "+j2.getScore());
			System.out.println("Joueur "+j.getNom()+" votre score augmente par 2 pour devenir: "+j.getScore());
		}
		else if ((nbree==6))
		{
			j2.CalculScore(1);
			j.CalculScore(3);
			System.out.println("Joueur "+j2.getNom()+" votre score augmente par 1 pour devenir: "+j2.getScore());
			System.out.println("Joueur "+j.getNom()+" votre score augmente par 3 pour devenir: "+j.getScore());
		}
		else if ((6<nbree)&&(nbree<=10))
		{
			j.CalculScore(4);
			System.out.println("Joueur "+j2.getNom()+" vous gagnez 0 points votre score reste: "+j2.getScore());
			System.out.println("Joueur "+j.getNom()+" votre score augmente par 4 pour devenir: "+j.getScore());
		}
		else
		{
			j.CalculScore(4);
			System.out.println("Joueur "+j2.getNom()+" vous avez perdu! votre score reste: "+j2.getScore());
			System.out.println("Le mot recherché était: "+s);
			System.out.println("Joueur "+j.getNom()+" votre score augmente par 4 pour devenir: "+j.getScore());
		}
		j.enregistrerJoueur();
		j2.enregistrerJoueur();
		}
		public static void main(String[] args)
		{
		}
	}
