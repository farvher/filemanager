package com.farvher.filemanager.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagesConstants {

	@Value("${resultados.cantidad.encontrados}")
	public String resultadosEncontrados;
	
	@Value("${archivos.cargado.correcto}")
	public String archivoCargadoCorrecto;
	
	@Value("${archivos.cargado.error}")
	public String archivoCargadoError;
	
	@Value("${directorio.creado.correcto}")
	public String directorioCreadoCorrecto;
	
	@Value("${directorio.creado.error}")
	public String directorioCreadoError;
	
	@Value("${directorio.creado.existe}")
	public String directorioCreadoExiste;
	
	@Value("${archivos.eliminado.correcto}")
	public String archivoEliminadoCorrecto;
	
	@Value("${archivos.eliminado.error}")
	public String archivoEliminadoError;

}
