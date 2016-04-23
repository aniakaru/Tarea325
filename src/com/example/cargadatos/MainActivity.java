package com.example.cargadatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String estado=Environment.getExternalStorageState();
		if(!estado.equals(Environment.MEDIA_MOUNTED)) //preguntamos si el sd card ha sido montada
		{
			Toast.makeText(this, "No hay SD card", 1).show(); //mostramos mensaje de que no esta montada la sd card
			finish(); //finalizamos la aplicacion
		}
		String linea;
		String pro[]=new String [6];
		String pre[]=new String [3];
		Toast.makeText(this,"corre.",1).show();
		try
		{
			File dir=Environment.getExternalStorageDirectory(); //agarramos el directorio
			File arch=new File(dir.getAbsolutePath()+File.separator+"bdproductos");//le estamos asignando el nombre del directorio del archivo
			File arch2=new File(dir.getAbsolutePath()+File.separator+"bdprecios");
			Scanner lee=new Scanner(new FileReader(arch)); //para leer el archivo
			Scanner lee2=new Scanner(new FileReader(arch2));
			BufferedReader lector = new BufferedReader(new FileReader(arch));
			BufferedReader lector2 = new BufferedReader(new FileReader(arch2));
			   String registro;
			Producto BaseDatos=new Producto (MainActivity.this);
			Venta BaseDatos2=new Venta (MainActivity.this);
			while((registro=lector.readLine())!=null)//miestras la lectura tenga un registro
			{

				pro=registro.split(";");
				try
		        {
		        	BaseDatos.apertura();
		        	BaseDatos.Insertar(Integer.parseInt(pro[0]),pro[1],pro[2],Integer.parseInt(pro[3]),pro[4],Integer.parseInt(pro[5]));
		        	BaseDatos.cerrar();
		        	System.out.println(pro[0]);
		        	Toast.makeText(this,"ok.",1).show();
		        }
				catch (Exception e) {
					// TODO: handle exception
		        	e.getMessage();
				}
				
			}
			System.out.println("no esta entrando al while");
			Toast.makeText(this,"no entra al while.",1).show();
			while((registro=lector2.readLine())!=null)//miestras la lectura tenga un registro
			{
				
				
				pre=registro.split(";");
				try
		        {
		        	BaseDatos2.apertura();
		        	BaseDatos2.Insertar(Integer.parseInt(pre[0]),pre[1],Double.parseDouble(pre[2]));
		        	BaseDatos2.cerrar();
		        	Toast.makeText(this,"ok.",1).show();
		        }
				catch (Exception e) {
					// TODO: handle exception
		        	e.getMessage();
				}
				
			}
		}
		catch(IOException e)
		{
			e.getMessage();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
