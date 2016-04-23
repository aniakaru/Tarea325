package com.example.cargadatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Producto {
	public static final String COD = "codigo";
	public static final String DES = "descripcion";
	public static final String UNV = "univen";
	public static final String UNE = "uniemp";
	public static final String LIN = "linea";
	public static final String UNEX = "existencia";
	
	private static final String BD= "Ventas.db";
	private static final String TB="Productos";
	private static final int VERSION =1;
	private ProcesoCentral Control;
	private final Context nContexto;
	private SQLiteDatabase pBD;
	private static class ProcesoCentral extends SQLiteOpenHelper
	{
		public ProcesoCentral (Context contexto)
		{
			super(contexto, BD, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+TB+" ( "+
						COD + " INTEGER PRIMARY KEY, "+
						DES + " TEXT NOT NULL, " +
						UNV + " TEXT NOT NULL, " +
						UNE + " INTEGER NOT NULL, " +
						LIN + " TEXT NOT NULL, " +
						UNEX + " INTEGER NOT NULL );"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int av, int nv) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+TB);
			onCreate(db);
			
		}
		
	
		
	}
	public Producto (Context c){
		nContexto=c;
	}
		
	public Producto apertura() throws Exception{
		Control = new ProcesoCentral(nContexto);
		pBD= Control.getWritableDatabase();
		return this;
	}
	public void cerrar(){
		Control.close();
	}
	public long Insertar(int qCod, String qDes,
			String qUve, int qUne,String qLin, int qUnex){
	ContentValues cv=new ContentValues();
	cv.put(COD,qCod);
	cv.put(DES,qDes);
	cv.put(UNV,qUve);
	cv.put(UNE,qUne);
	cv.put(LIN,qLin);
	cv.put(UNEX,qUnex);
	
	return pBD.insert(TB, null, cv); // devuelve -1 si hubo error de insercion
	
	}

}
