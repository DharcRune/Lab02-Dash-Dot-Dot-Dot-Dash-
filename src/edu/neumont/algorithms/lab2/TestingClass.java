package edu.neumont.algorithms.lab2;

import java.util.List;

public class TestingClass 
{
	public static void main(String[] args) 
	{
		ExhaustiveDecoder decoder = new ExhaustiveDecoder();
		
		List<String> possibilities = decoder.decode(".-......-........-....-.-...--...----..----..-.-.-.....----.-.---.-...--....");
		
		for ( int i = 0; i < possibilities.size(); i++ ) 
		{
			System.out.println(i + ": " + possibilities.get(i));
		}
	}

}
