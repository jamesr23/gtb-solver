package com.jamesr.gtbsolver;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class GTBCommand extends CommandBase {
	
	private ArrayList<String> aliases;

	public GTBCommand() {
		aliases = new ArrayList<String>();
		aliases.add("gtb");
	}
	
	@Override
	public String getCommandName() {
		return "gtb";
	}

	@Override
	public List<String> getCommandAliases(){
		return aliases;
	}
	
	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return null;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if(args.length < 1) {
			sender.addChatMessage(new ChatComponentText("[gtbsolver] " + EnumChatFormatting.RED + "error" + EnumChatFormatting.WHITE + " enter the hint"));
			return;
		}
		
		String hint = args[0];
		
		for(int i = 1; i < args.length; i++)
			hint += " " + args[i];
		
		sender.addChatMessage(new ChatComponentText("[gtbsolver] matching words for " + hint));

		ArrayList<String> res = match(hint);
		
		if(res.size() == 0)
			sender.addChatMessage(new ChatComponentText("[gtbsolver] no matches found"));
		
		for(int i = 0; i < res.size(); i++)
			sender.addChatMessage(new ChatComponentText((i + 1) + ": " + res.get(i)));

	}
	
	private ArrayList<String> match(String hint) {
		
		ArrayList<String> matches = new ArrayList<String>();
		int length = hint.length();
		
		if(!GTBSolver.words.containsKey(length))
			return matches;
		
		for(String word : GTBSolver.words.get(length)) {
			if(checkWord(hint, word))
				matches.add(word);
		}
		return matches;
	}
	
	private boolean checkWord(String hint, String word) {
		
		// assuming that the lengths are the same
		int length = hint.length();
		
		for(int i = 0; i < length; i++) {
			if(hint.charAt(i) == '_')
				continue;
			if(hint.charAt(i) != word.charAt(i))
				return false;
		}
		return true;
	}
}
