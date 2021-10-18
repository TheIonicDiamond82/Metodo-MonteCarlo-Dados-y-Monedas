package metodo_de_montecarlo;

import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
Peñuelaz Leyva Luis Alberto
Uriarte Avelar Jesus Eduardo
*/
public class Metodo_de_MonteCarlo {

                static double PDado[]=new double [6];
                static double PDadoAcum[]=new double [6];
                static double PAnt[]=new double [6];
                static double PPost[]=new double [6];
		static double PMoneda[]=new double [2];
		static int Dado[]=new int [6];
		static int Moneda[]=new int [2];
	public static void main(String[] args) {
		
		Scanner Ent=new Scanner(System.in);
		int Vueltas=0;
		int D,M;
		boolean Validar;
		double P;
		char OP=' ';
		do
		{
		System.out.println("Que operacion decea ralizar Moneda[M] O Dado[D]");
		OP=Ent.next().charAt(0);
		if(OP=='M'||OP=='D')
		{
			Validar=true;
		}else
		{
			Validar=false;
		}
		
		}while(Validar!=true);
		
		do
		{
                    if(OP == 'M')
                    {
                    System.out.println("¿Cuantas veces se lanzara la Moneda?");
                    Vueltas=Ent.nextInt();	
                    }
                    if(OP == 'D')
                    {
                    System.out.println("¿Cuantas veces se lanzara el Dado?");
                    Vueltas=Ent.nextInt();	
                    }
                    
		if(Vueltas>0 && Vueltas<=1000000)
		{
			Validar=true;
		}
		else
		{
			Validar =false;
		}
			
		}while (Validar!=true);
		
		if(OP=='M')
		{
                    
			for(int x=0;x<Vueltas;x++)
			{
			M= (int)(Math.random()*2);
				if(M <= 0.5)
				{
				Moneda[0]++;	
				}
                                else if(M<=1.0 && M>0.5)
				{
				Moneda[1]++;
				}
			}
                        P=1000000/Vueltas;
			PMoneda[0]=(P*Moneda[0]);
			PMoneda[1]=(P*Moneda[1]);
                        System.out.println("Evento\t Probabilidad\t P.Acumulada\t Intervalo\t");
			System.out.println("Aguila\t    " + PMoneda[0]/1000000+"\t\t  "+(PMoneda[0]/1000000)+"\t\t"+"0.00 - "+(PMoneda[0]/1000000));
			System.out.println("Sello\t    " + PMoneda[1]/1000000+"\t\t  "+(PMoneda[0]/1000000+PMoneda[1]/1000000)+"\t\t"+(PMoneda[0]/1000000)+" - "+(PMoneda[0]/1000000+PMoneda[1]/1000000));
			
		}
		if(OP=='D')
		{
			for(int x=0;x<Vueltas;x++)
			{
				D=(int)(Math.random()*7);
				if(D==0)D=1;
				switch (D)
				{
				case 1:
					Dado[0]++;
					break;
				case 2:
					Dado[1]++;
					break;
				case 3:
					Dado[2]++;
					break;
				case 4:
					Dado[3]++;
					break;
				case 5:
					Dado[4]++;
					break;
				case 6:
					Dado[5]++;
					break;
					
				}
				
			}
				
                        System.out.println("Evento\t   Probabilidad\t   P.Acumulada\t  Intervalo\t");
			for(int x=0; x<6;x++)
			{
                        P=1000000/Vueltas;
                        PDado[x]=P*Dado[x];
                        PPost[x]=PAnt[x]+PDado[x];
                        if(x ==0)PDadoAcum[0]=PDado[0];
                        if(x>0 && x<= 6) PDadoAcum[x]=PDadoAcum[x-1]+PDado[x];
                        if(x==0)
                        {
			System.out.println("   "+(x+1)+ "\t\t" + PDado[x]/1000000+"\t\t"+PDadoAcum[x]/1000000+"\t   "+"0.0-"+PDado[x]/1000000);
                        }
                        if(x>0 && x<= 6) 
                        {
                         System.out.println("   "+(x+1)+ "\t\t" + PDado[x]/1000000+"\t\t"+PDadoAcum[x]/1000000+"\t   "+PDadoAcum[x-1]/1000000+"-"+PDadoAcum[x]/1000000);   
                        }
			}
                }
		pastel(OP);
		
		
		
	}
	  public static void pastel(char OP) 
	  {
		  DefaultPieDataset dataset = new DefaultPieDataset();  
		  if(OP=='M')
                  {
                    dataset.setValue("Aguila", PMoneda[0]);
		    dataset.setValue("Sello", PMoneda[1]);
		   
                  }
                  if(OP == 'D')
                  {
                    dataset.setValue("Numero 1", PDado[0]);
		    dataset.setValue("Numero 2", PDado[1]);
		    dataset.setValue("Numero 3", PDado[2]);
		    dataset.setValue("Numero 4", PDado[3]);
                    dataset.setValue("Numero 5", PDado[4]);
                    dataset.setValue("Numero 6", PDado[5]);
                  }
		    
		    
		    JFreeChart grafica = ChartFactory.createPieChart3D(
		    	    "Grafica De Pastel Metodo de MonteCarlo", // El nombre de la gráfica
		    	    dataset, // El arreglo de datos
		    	    true, // Indica si lleva leyenda
		    	    true, // Indica si lleva tooltips
		    	    false); // Indica si son URls}
		    ChartFrame frame = new ChartFrame("Graficador", grafica);
		    frame.pack();
		    frame.setVisible(true);
	  }
}