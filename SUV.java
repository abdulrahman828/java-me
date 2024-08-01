import java.io.Serializable;

public class SUV extends Vehicle   implements Serializable{
	private static final long serialVersionUID = 1L;

	private int days;
	
	
	private final  static double COMISSION=15;
	
	public SUV(String name, int id,  double carCost, String color, String company,int days,String n ,double se) throws InfoException  {
		super(name, id, carCost, color, company,n,se);
		this.days=days;
		
		
		
	}
	public SUV(SUV v) {
           super(v);
		this.days=v.days;
	}
	@Override
	public double CalcCost() {
		
			return (CarCost*days)+COMISSION+getInsuranceCost();
	}
	@Override
	public String toString() {
		return "SUV "+super.toString()+"days=" + days                                   +"\n"+"-----------------------------------------";
	}
	public static double getComission() {
		return COMISSION;
	}
	

}
