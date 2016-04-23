package com.example.cargadatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Venta {

	public static final String COD = "codigo";
	public static final String FECHA = "fecha";
	public static final String PRE = "precio";
	
	private static final String BD= "Ventas.db";
	private static final String TB="Ventas";
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
						FECHA + " TEXT NOT NULL, " +
						PRE + " DECIMAL(6,2) NOT NULL );"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int av, int nv) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+TB);
			onCreate(db);
			
		}
		
	
		
	}
	public Venta (Context c){
		nContexto=c;
	}
		
	public Venta apertura() throws Exception{
		Control = new ProcesoCentral(nContexto);
		pBD= Control.getWritableDatabase();
		return this;
	}
	public void cerrar(){
		Control.close();
	}
	public long Insertar(int qCod, String qFech,double qPre){
	ContentValues cv=new ContentValues();
	cv.put(COD,qCod);
	cv.put(FECHA,qFech);
	cv.put(PRE,qPre);
	return pBD.insert(TB, null, cv); // devuelve -1 si hubo error de insercion
	
	}

}
