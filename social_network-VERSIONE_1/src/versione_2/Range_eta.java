package versione_2;

import java.io.Serializable;

public enum Range_eta implements Serializable {
	GIOVANI("1-17",1,17),
	GIOVANI_ADULTI("18-25",18,25),
	ADULTI("26-40",26,40),
	OVER_40("40-100",40,100);
	
	private String range;
	private int min;
	private int max;
	
	private Range_eta(String s,int min,int max) {
		this.range=s;
		this.min=min;
		this.max=max;
	}

	public String getRange() {
		return range;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
	
	public static String visualizzaRange() {
		StringBuffer str=new StringBuffer();
		Range_eta [] range=Range_eta.values();
		for(int i=0;i<range.length;i++){
			str.append(i+1+")").append(range[i].getRange()).append("\n");
		}
		return str.toString();
	}
	
}
