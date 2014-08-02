package edu.neumont.algorithms.lab2;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import edu.neumont.nlp.DecodingDictionary;

public class ExhaustiveDecoder
{	
	 private DecodingDictionary decodeDictionary = new DecodingDictionary(); 
	 public List <String> permutations = new ArrayList<String>();
	 public List <ExhaustiveDecoderHelper> possibleSentances = new ArrayList<ExhaustiveDecoderHelper>();
	 private  float wordThreshHold =  .0001f;
	 float sentanceThreshHold =  10000.0f;
	
	 public List<String> decode (String message)
	 {
		decrypter("",message);
		return lastPassThough();
	 }
	
	public void decrypter(String sentance, String currentCode)
	{
		float k = checkSentance(sentance);
		
		if(sentance.isEmpty() || k > wordThreshHold)
		{
			if(currentCode.isEmpty())
			{
				possibleSentances.add(new ExhaustiveDecoderHelper(checkSentance(sentance), sentance));
			}
				
			else
			{
				int index = 0;
				int end =  currentCode.length();
				
				while(index <= end)
				{
					String charater = currentCode.substring(0,index);
					Set <String>  possibleWords = decodeDictionary.getWordsForCode(charater);
				  
					if(possibleWords != null)
					{
					  String remaning = currentCode.substring(index);
					 
					  for(String s :  possibleWords)
					  {  
						  String newWords =  sentance + s + " ";
						  decrypter(newWords, remaning);
					  }
				  }
					
					index++;
				}
			}	
		}
	}
	
	public List<String>lastPassThough()
	{
		if(possibleSentances.size() != 0)
		{
			Collections.sort(possibleSentances);
			permutations = new ArrayList<String>();
			final int TOPTWENTY = 20;
		
			for(int i = 0; i < TOPTWENTY && i < possibleSentances.size(); i++)
			{
				ExhaustiveDecoderHelper now  = possibleSentances.get(i);
				permutations.add( now.toString());
			}
		}
		
		return permutations;
	}
	
	public float checkSentance(String sentance)
	{
		String [] splitString = sentance.split(" ");
		float frequency =  1.0f;
		String lastWord = "";
			
		for(String s : splitString)
		{
			if(lastWord.isEmpty())
			{
				lastWord = s;
			}
			else
			{
				frequency *= (decodeDictionary.frequencyOfFollowingWord(lastWord, s) / sentanceThreshHold);
				lastWord = s;
			}
		}
		return frequency;
	}
}