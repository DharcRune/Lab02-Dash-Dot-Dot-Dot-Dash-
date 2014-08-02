package edu.neumont.algorithms.lab2;

public class ExhaustiveDecoderHelper implements Comparable<ExhaustiveDecoderHelper>
{
	public float frequancy;
	public String word;
	
	public ExhaustiveDecoderHelper(float freq, String str)
	{
		frequancy = freq;
		word = str;
	}
	
	@Override
	public int compareTo(ExhaustiveDecoderHelper arg0) 
	{
		int result=0;
		if(arg0.frequancy > this.frequancy)
		{
			result = 1;
		}
		
		else if(arg0.frequancy < this.frequancy)
		{
			result = -1;
		}
		return result;
	}
	
	@Override
	public String toString()
	{
		return word + " Frequency: " + " " + frequancy; 
	}
}
