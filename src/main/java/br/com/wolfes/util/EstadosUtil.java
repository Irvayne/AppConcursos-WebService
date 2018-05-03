package br.com.wolfes.util;

import java.util.ArrayList;
import java.util.List;

import br.com.wolfes.model.Estado;

public class EstadosUtil {

	public static List<Estado> listaEstados() {
		ArrayList<Estado> estados = new ArrayList<Estado>();
		//TODO fazer para todos
		estados.add(new Estado("pi", "Piauí"));
		estados.add(new Estado("ma", "Maranhão"));
		estados.add(new Estado("ce", "Ceará"));
		
		return estados;
	}

}
