package Pendu;
import java.io.IOException;
import java.util.Scanner;

public class Pendu 
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Bienvenue au jeux Pendu");
		System.out.println("1-Jouer");
		System.out.println("2-Quitter");
		int n=sc.nextInt();
		sc.nextLine();
		for (int i=0;i<20;i++)
		{
			System.out.println("\n");
		}
		while(n==1)
		{
			//initialisation des parametres
			Jeu j= new Jeu();
			int n1=j.fen1(sc);
			int n2=4;
			while (n2==4)
			{
				n2=j.fen2(sc);
				if (n2==4)
				{
					n1=j.fen1(sc);
				}
			}
			j.set_d(n1);
			j.set_m(n2);
			if (n2==2)
			{
				StringBuffer ch1=new StringBuffer("");
				while (ch1.toString().equals(""))
				{
				System.out.println("Joueur 1 donnez votre nom s'il vous plait:");
				ch1.append(sc.nextLine());
				}
				Joueur player1= new Joueur(ch1.toString());
				StringBuffer ch2=new StringBuffer("");
				while (ch2.toString().equals(""))
				{
				System.out.println("Joueur 2 donnez votre nom s'il vous plait:");
				ch2.append(sc.nextLine());
				}
				Joueur player2= new Joueur(ch2.toString());
				j.jouer2(player1,player2, sc);
			}
			else
			{
				StringBuffer ch=new StringBuffer("");
				while (ch.toString().equals(""))
				{
				System.out.println("Donnez votre nom s'il vous plait:");
				ch.append(sc.nextLine());
				}
				Joueur player1= new Joueur(ch.toString());
				j.jouer1(player1, sc);
			}
			//permet de  rejouer
			System.out.println("Voulez vous rejouer?");
			System.out.println("1-Oui");
			System.out.println("2-Non");
			n=sc.nextInt();
			for (int i=0;i<20;i++)
			{
				System.out.println("\n");
			}
		}
		sc.close();
	}
}

