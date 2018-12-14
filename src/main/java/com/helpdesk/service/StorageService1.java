//package com.helpdesk.service;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import javax.servlet.ServletContext;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.FileSystemUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import ch.qos.logback.core.net.SyslogOutputStream;
// 
// 
//@Service
//public class StorageService1 {
//	@Autowired
//	private ServletContext context;
//	//Save the uploaded file to this folder
//    private String UPLOAD_FOLDER =  context.getRealPath("C://temp//");
//    
//	Logger log = LoggerFactory.getLogger(this.getClass().getName());
//	private final Path rootLocation = Paths.get(UPLOAD_FOLDER);
//	
// 
//	public void store(MultipartFile file){
//		System.out.println(UPLOAD_FOLDER);
//		System.out.println(rootLocation.toString());
//		try {
//            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
//        	//System.out.println(file.getOriginalFilename());
//        } catch (Exception e) {
//        	throw new RuntimeException("FAIL!");
//        }
//	}
// 
//    public Resource loadFile(String filename) {
//        try {
//            Path file = rootLocation.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }else{
//            	throw new RuntimeException("FAIL!");
//            }
//        } catch (MalformedURLException e) {
//        	throw new RuntimeException("FAIL!");
//        }
//    }
//    public String loadFileByName(String filename) {
//    	try {
//    		 Path file = rootLocation.resolve(filename);
//    		 System.out.println(file.toUri().toString());
//    		 return file.toUri().toString();
//    		 
//    	}catch(Exception e) {
//    		
//    	}
//    	return null;
//    }
//    
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }
// 
//    public void init() {
//        try {
//            Files.createDirectory(rootLocation);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
//    }
//}