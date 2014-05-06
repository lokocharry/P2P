package Persistence;

public class Category {
	
	public final static String NULL="";
	public final static String PELICULAS="Peliculas";
	public final static String MUSICA="Musica";
	public final static String SERIES="Series";
	public final static String JUEGOS="Juegos";
	public final static String IMAGENES="Imagenes";
	public final static String PROGRAMAS="Programas";
	public final static String LIBROS="Libros";
	
	public static String[] getCategorys(){
		String [] aux=new String [8];
		aux[0]=NULL;
		aux[1]=PELICULAS;
		aux[2]=MUSICA;
		aux[3]=SERIES;
		aux[4]=JUEGOS;
		aux[5]=IMAGENES;
		aux[6]=PROGRAMAS;
		aux[7]=LIBROS;
		return aux;
	}

}
