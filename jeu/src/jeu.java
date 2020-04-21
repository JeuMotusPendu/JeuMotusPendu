import java.util.Scanner;
import java.io.*;
public class jeu 
{
	protected int difficulte;
	protected int mode;
	public jeu()
	{
		difficulte=0;
		mode=0;
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
		return n;
	}
	public int fen2(Scanner sc)
	{
		System.out.println("Options:");
		System.out.println("1-2 Joueur");
		System.out.println("2-Contre la montre");
		System.out.println("3-Retour");
		int n=sc.nextInt();
		sc.nextLine();
		return n;
			
	}
	public void jouer(Joueur j,Scanner sc) throws FileNotFoundException
	{
		if (mode == 1)//mode deux joueurs
		{
			//intialisation des paramètres
			System.out.println("Donnez le mot a deviner:");
			String ch=sc.nextLine();
			StringBuffer s= new StringBuffer();
			s.append(ch);
			Mot m = new Mot(s);
			System.out.flush();
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
			System.out.println("Vous avez "+nbrt+" tentatives");
			while((m.getmot()!=m.getmasque())&&(nbrt<m.nbt()))
			{
				System.out.println("Donnez un carctère: ");
				char c=(char) sc.nextInt();
				if (m.comparer(c))
				{
					m.setmasque(c);
				}
				else
				{
					nbree++;
				}
			}
			//calcul du score et affichage du resultat
			if (nbree<2)
			{
				j.CalculScore(4);
				System.out.println("Felicitation! Votre score augmente par 4 pour devenir: "+j.getscore());
			}
			if ((1<nbree)&&(nbree<4))
			{
				j.CalculScore(3);
				System.out.println("Felicitation! Votre score augmente par 3 pour devenir: "+j.getscore());
			}
			if ((3<nbree)&&(nbree<6))
			{
				j.CalculScore(2);
				System.out.println("Felicitation! Votre score augmente par 2 pour devenir: "+j.getscore());
			}
			if (nbree==6)
			{
				j.CalculScore(1);
				System.out.println("Felicitation! Votre score augmente par 1 pour devenir: "+j.getscore());
			}
			if ((6<nbree)&&(nbrt<m.nbt()))
			{
				System.out.println("Désolé vous gagnez 0 points votre score reste: "+j.getscore());
			}
			else
			{
				System.out.println("Désolé vous avez perdu!");
				System.out.println("Le mot recherché était: "+s);
			}
		}
		if (this.mode == 2)//mode contre la montre
		{
			//intialisation des paramètres
			Mot m = new Mot();
			int nbrt =m.nbt();
			int nbree = 0;
			System.out.println("Vous avez "+nbrt+" tentatives");
			//intialisation du masque en cas de difficulé facile
			StringBuffer s=m.getmot();
			if (difficulte==1)
			{
				m.setmasque(s.charAt(0));
				m.setmasque(s.charAt(s.length()-1));
			}
			//deroulement du jeu
			System.out.println("Vous avez une minute pour deviner le mot");
			long temps=0;//intialisation du compteur du temps
			long startTime =System.currentTimeMillis();//commencement
			while((m.getmot()!=m.getmasque())&&(nbrt<m.nbt())&&(temps<=60000))
			{
				nbrt++;
				System.out.println("Donnez un caractre: ");
				char c=(char) sc.nextInt();
				if (m.comparer(c))
				{
					m.setmasque(c);
				}
				else
				{
					nbree++;
				}
				long endTime = System.currentTimeMillis();
				temps= endTime-startTime;//calcul du temps passé
			}
			//calcul du score et affichage du resultat
			if ((nbree<2)&&(temps<=60000))
			{
				j.CalculScore(4);
				System.out.println("Felicitation! Votre score augmente par 4 pour devenir: "+j.getscore());
			}
			if ((1<nbree)&&(nbree<4)&&(temps<=60000))
			{
				j.CalculScore(3);
				System.out.println("Felicitation! Votre score augmente par 3 pour devenir: "+j.getscore());
			}
			if ((3<nbree)&&(nbree<6)&&(temps<=60000))
			{
				j.CalculScore(2);
				System.out.println("Felicitation! Votre score augmente par 2 pour devenir: "+j.getscore());
			}
			if ((nbree==6)&&(temps<=60000))
			{
				j.CalculScore(1);
				System.out.println("Felicitation! Votre score augmente par 1 pour devenir: "+j.getscore());
			}
			if ((6<nbree)&&(nbree<=nbrt)&&(temps<=60000))
			{
				System.out.println("Désolé vous gagnez 0 points votre score reste: "+j.getscore());
			}
			else
			{
				System.out.println("Désolé vous avez perdu!");
				System.out.println("Le mot recherché était: "+s);
			}
		}
	}
	public static void main(String[] args)
	{
		/*jeu j= new jeu();
		Scanner sc=new Scanner(System.in);
		int n1=j.fen1(sc);
		int n2=j.fen2(sc);
		if (n2==4)
			n1=j.fen1(sc);
		j.set_d(n1);
		j.set_m(n2);
		System.out.println(j.get_d()+","+j.get_m());
		sc.close();*/
	}
}
