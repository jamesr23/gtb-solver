package com.jamesr.gtbsolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = GTBSolver.MODID, version = GTBSolver.VERSION)

public class GTBSolver {

    public static final String MODID = "gtbsolver";
    public static final String VERSION = "1.0";
    
    public static HashMap<Integer, ArrayList<String>> words;
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	BufferedReader br;
    	try {
			br = new BufferedReader(new FileReader(new File(Loader.instance().getConfigDir(), "GTBSolver.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("GTBSolver.txt not found");
			return;
		}
    	
    	MinecraftForge.EVENT_BUS.register(this);
    	ClientCommandHandler.instance.registerCommand(new GTBCommand());
    	
    	words = new HashMap<Integer, ArrayList<String>>();
    	
    	String line;
    	  
    	try {
			while((line = br.readLine()) != null)
				addWord(line);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error reading file");
			return;
		}
    }

    public static void addWord(String word) {
    	
    	int length = word.length();
    	
    	if(!words.containsKey(length))
    		words.put(length, new ArrayList<String>());
    	
    	words.get(length).add(word);
    	return;
    }
}
